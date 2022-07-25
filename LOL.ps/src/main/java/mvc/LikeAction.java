package mvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.yg_ac.dao.BoardDao;
import com.yg_ac.dto.ChampBasicStatDto;

public class LikeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		BoardDao bDao = new BoardDao();
		int bno = Integer.parseInt(request.getParameter("bno"));
		int memberkey = Integer.parseInt(request.getParameter("memberkey"));		
		
		//싫어요에 정보가 있으면 
		//그거 삭제 후 좋아요 
		//내가 글 싫어요를 누른상태면 
		int bad = bDao.badCheck(memberkey, bno);
		//그거 삭제 후 싫어요 
		if(bad == 1) {
			System.out.print("여기들어옴!");
			bDao.mybadDelete(bno,memberkey);
			bDao.communityGood(request, response, bno, memberkey);
		}
		//내가 싫어요 혹은 좋아요 눌르지 않았다면 . . .
		//원래 갯수 확인 .
		else if (bad == 0) {
			if(bDao.badCount(bno) > 0) {
				bDao.badDelete(bno);
			}
			System.out.print("else 여기들어옴!");
			bDao.communityGood(request, response, bno, memberkey);
			}
	
}

}
