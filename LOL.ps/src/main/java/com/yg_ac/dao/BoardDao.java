package com.yg_ac.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yg_ac.dto.BoardDto;
import com.yg_ac.dto.CommentDto;
import com.yg_ac.dto.MemberDTO;

public class BoardDao {
	Y_DBmanager db = new Y_DBmanager();
	Connection conn = db.getConnection();
	
	public ArrayList<BoardDto> getBoardList(String category, int startBno, int endBno){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * " + 
				"from( " + 
				"    select rownum rnum, b1.* " + 
				"    from( " + 
				"        select * " + 
				"        from community " + 
				"        where category = ? " + 
				"        order by bno desc) b1) " + 
				"where rnum >= ? " + 
				"and rnum <= ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, startBno);
			pstmt.setInt(3, endBno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int memberkey = rs.getInt("memberkey");
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writedate = rs.getString("writedate");
				int good = rs.getInt("good");
				int bad = rs.getInt("bad");
				int count = rs.getInt("count");
				String champName = rs.getString("champname");
				list.add(new BoardDto(memberkey,bno,title,content,writedate,good,bad,count,champName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public int getAllBoardList(String category){
		int cnt=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) cnt from community where category = ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	public String getImage(String champName) {
		String image = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select image_head image " + 
				"from champ_skill " + 
				"where name = ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,champName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				image = rs.getString("image");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return image;
	}
	public MemberDTO getWriter(int memberkey) {
		MemberDTO member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select nickname name, image, introduce " + 
				"from member " + 
				"where memberkey = ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,memberkey);
			rs = pstmt.executeQuery();
			rs.next();
			String writer = rs.getString("name");
			String image = rs.getString("image");
			String introduce = rs.getString("introduce");
			member = new MemberDTO(writer,image,introduce);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return member;
	}
	public BoardDto getDetail(int bno) {
		BoardDto detail = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * " + 
				"from community " + 
				"where bno = ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			rs.next();
			int memberkey = rs.getInt("memberkey");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String writedate = rs.getString("writedate");
			int good = rs.getInt("good");
			int bad = rs.getInt("bad");
			int count = rs.getInt("count");
			String category = rs.getString("category");
			String champName = rs.getString("champname");
			detail = new BoardDto(memberkey,title,content,writedate,good,bad,count,category,champName);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return detail;
	}
	public void writeAction(int memberkey, String title, String content, String category, String champName) {
		String sql = "insert into community values ( ? , BNO_SEQ.nextval, ? , ? , sysdate, 0, 0, 0, ? , ? )";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,memberkey);
			pstmt.setString(2,title);
			pstmt.setString(3,content);
			pstmt.setString(4,category);
			pstmt.setString(5,champName);
			pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//????????? ??????
	public void updateBoard(int bno , String title , String content) {
		String sql = "update community set title = ? , content = ? " +
					" where bno = ? ";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, bno);
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//????????? ??????
	public void deleteBoard(int bno) {
		String sql = " delete from community where bno = ?  ";
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		public ArrayList<Integer> getFirstLastBno(String category) {
		ArrayList<Integer> get = new ArrayList<Integer>();
		String sql = "select * from community where category = ? order by bno desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bno = rs.getInt("bno");
				get.add(bno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return get;
	}
	public ArrayList<BoardDto> getBoardListTitle(String category, String name, int startBno, int endBno){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * " + 
				"from( " + 
				"    select rownum rnum, b1.* " + 
				"    from( " + 
				"        select * " + 
				"        from community " + 
				"        where category = ? " + 
				"        and title like ? " + 
				"        order by bno desc) b1) " + 
				"where rnum >= ? " + 
				"and rnum <= ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2,"%" + name + "%");
			pstmt.setInt(3, startBno);
			pstmt.setInt(4, endBno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int memberkey = rs.getInt("memberkey");
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writedate = rs.getString("writedate");
				int good = rs.getInt("good");
				int bad = rs.getInt("bad");
				int count = rs.getInt("count");
				String champName = rs.getString("champname");
				list.add(new BoardDto(memberkey,bno,title,content,writedate,good,bad,count,champName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public int getAllBoardListTitle(String category, String name){
		int cnt=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) cnt from community where category = ? and title like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2,"%" + name + "%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	public ArrayList<BoardDto> getBoardListWriter(String category, int name, int startBno, int endBno){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * " + 
				"from( " + 
				"    select rownum rnum, b1.* " + 
				"    from( " + 
				"        select * " + 
				"        from community " + 
				"        where category = ? " + 
				"        and memberkey = ? " + 
				"        order by bno desc) b1) " + 
				"where rnum >= ? " + 
				"and rnum <= ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, name);
			pstmt.setInt(3, startBno);
			pstmt.setInt(4, endBno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int memberkey = rs.getInt("memberkey");
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writedate = rs.getString("writedate");
				int good = rs.getInt("good");
				int bad = rs.getInt("bad");
				int count = rs.getInt("count");
				String champName = rs.getString("champname");
				list.add(new BoardDto(memberkey,bno,title,content,writedate,good,bad,count,champName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public int getAllBoardListWriter(String category, int name){
		int cnt=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) cnt from community where category = ? and memberkey = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	public int getNicknameMemberkey(String nickname) {
		int get = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where nickname like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname + "%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				get = rs.getInt("memberkey");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return get;
	}
	// ?????? ??????
	public void insertComment(int memberkey, int bno, String content) {
		String sql = "insert into community_comment values(?, ?, cno_seq.nextval, rno_seq.nextval, ?, sysdate)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberkey);
			pstmt.setInt(2, bno);
			pstmt.setString(3, content);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void insertReply(int memberkey, int bno, int cno, String content) {
		String sql = "insert into community_comment values(?, ?, ?, rno_seq.nextval, ?, sysdate)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberkey);
			pstmt.setInt(2, bno);
			pstmt.setInt(3, cno);
			pstmt.setString(4, content);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// ?????? ????????????
	public ArrayList<CommentDto> getCommnet(int bno) {
		ArrayList<CommentDto> get = new ArrayList<CommentDto>();
		String sql = "select * from community_comment where bno = ? order by cno,rno";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int memberkey = rs.getInt("memberkey");
				int cno = rs.getInt("cno");
				int rno = rs.getInt("rno");
				String content = rs.getString("content");
				String writedate = rs.getString("writedate");
				get.add(new CommentDto(memberkey, bno, cno, rno, content, writedate));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return get;
	}
	//?????? ????????????
	public void DeleteComment (int cno) {
		String sql = " DELETE FROM COMMUNITY_COMMENT WHERE cno = ? " ;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cno);
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//????????? ??????
	public void DeleteCommentComments (int rno) {
		String sql = " DELETE FROM COMMUNITY_COMMENT WHERE rno = ? " ;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//?????? ??????
	public void updateComment (String content,int rno ) {
		String sql = " UPDATE COMMUNITY_COMMENT SET CONTENT = ? where rno = ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, rno);
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	// ?????????
	public void insertViews(int bno) {
		String sql = "update community set count = count+1 where bno = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//????????? ????????? ???
	public void communityGood(HttpServletRequest request, HttpServletResponse response, int bno , int memberkey) throws  ServletException, IOException, SQLException {
		PreparedStatement pstmt = null;
		response.setCharacterEncoding("UTF-8");
		

		try {			
			String sql = " insert into community_like values( ? , ? ) ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setInt(2, memberkey);
			pstmt.executeUpdate();
//			request.setAttribute("like", likeCount(bno));
//			request.getRequestDispatcher("ViewDetail.jsp?bno="+bno).forward(request, response);
//			response.sendRedirect("ViewDetail.jsp?bno="+bno);
			response.getWriter().print(likeCount(bno));
			pstmt.close();
		}
		//?????? ???????????? 2??? ????????? ??????
		catch (SQLException e) {
			e.printStackTrace();
			String sql = "select count(*) cnt from community_like where bno = ? and memberkey = ? ";			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setInt(2, memberkey);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt("cnt");
			if(cnt == 1 ) {
				response.sendError(HttpServletResponse.SC_CREATED);
				response.getWriter().print(likeCount(bno));
//				response.sendRedirect("ViewDetail.jsp?bno="+bno);
				pstmt.close();
			}				
		}		
}
	//????????? ??? ??????
	public int likeCount(int bno) {
		PreparedStatement pstmt = null;
		int like = 0;
		try {
			String sql = " select count(*) count from community_like where bno = ? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			like = rs.getInt("count");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return like;
	}
	//????????? ??? ??????
	public int badCount(int bno) {
		PreparedStatement pstmt = null;
		int like = 0;
		try {
			String sql = "select count(*) count from community_bad where bno = ? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			like = rs.getInt("count");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return like;
	}
	//???????????? ?????? ??? ???????????? ????????????
	public int likeCheck(int memberkey , int bno) {
		PreparedStatement pstmt = null;
		int cnt = 0;
		String sql = " select count(*) cnt from community_like where bno = ? and memberkey = ? ";
		try {
			pstmt =  conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setInt(2, memberkey);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	//???????????? ?????? ??? ???????????? ????????????
	public int badCheck(int memberkey , int bno) {
		PreparedStatement pstmt = null;
		int cnt = 0;
		String sql = " select count(*) cnt from community_bad where bno = ? and memberkey = ? ";
		try {
			pstmt =  conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setInt(2, memberkey);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	//?????? ?????? ????????? ??????
	public void mylikeDelete(int bno , int memberkey) {
		PreparedStatement pstmt = null;
		try {
			String sql = " delete from community_like where bno = ? and memberkey =  ? " ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setInt(2, memberkey);
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void likeDelete(int bno ) {
		PreparedStatement pstmt = null;
		try {
			String sql = " delete from community_like where bno = ? and ROWNUM =  1 " ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void mybadDelete(int bno ,int memberkey) {
		PreparedStatement pstmt = null;
		try {
			String sql = " delete from community_bad where bno = ? and memberkey =  ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setInt(2, memberkey);
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void badDelete(int bno) {
		PreparedStatement pstmt = null;
		try {
			String sql = " delete from community_bad where bno = ? and ROWNUM =  1 ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void communityBad(HttpServletRequest request, HttpServletResponse response, int bno , int memberkey) throws  ServletException, IOException, SQLException {
		PreparedStatement pstmt = null;
		response.setCharacterEncoding("UTF-8");
		

		try {			
			String sql = " insert into community_bad values( ? , ? ) ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setInt(2, memberkey);
			pstmt.executeUpdate();
//			request.setAttribute("like", likeCount(bno));
//			request.getRequestDispatcher("ViewDetail.jsp?bno="+bno).forward(request, response);
//			response.sendRedirect("ViewDetail.jsp?bno="+bno);
			response.getWriter().print(badCount(bno));
			pstmt.close();
		}
		//?????? ???????????? 2??? ????????? ??????
		catch (SQLException e) {
			e.printStackTrace();
			String sql = "select count(*) cnt from community_bad where bno = ? and memberkey = ? ";			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setInt(2, memberkey);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt("cnt");
			if(cnt == 1 ) {
				response.sendError(HttpServletResponse.SC_CREATED);
				response.getWriter().print(badCount(bno));
//				response.sendRedirect("ViewDetail.jsp?bno="+bno);
				
			}
				
				
				
				
		
		}
		}
}
