package mvc;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yg_ac.dao.BoardDao;

public class BadAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		BoardDao bDao = new BoardDao();
		int bno = Integer.parseInt(request.getParameter("bno"));
		int memberkey = Integer.parseInt(request.getParameter("memberkey"));
		
		//글 좋아요를 누른상태면 
		int like = bDao.likeCheck(memberkey, bno);
		//그거 삭제 후 싫어요 
		if(like == 1) {
			System.out.print("여기들어옴!");
			bDao.mylikeDelete(bno, memberkey);
			bDao.communityBad(request, response, bno, memberkey);
		}
		//싫어요 혹은 좋아요 눌리지 않았다면 . . .
		else if (like == 0) {
			//좋아요에 하나라도 데이터가 있으면 . . .
			if(bDao.likeCount(bno) > 0) {
				bDao.likeDelete(bno);
			}
			System.out.print("else 여기들어옴!");
			bDao.communityBad(request, response, bno, memberkey);
		}
		
	}

}
