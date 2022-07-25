package mvc;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yg_ac.dao.BoardDao;

public class LikeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		BoardDao bDao = new BoardDao();
		int bno = Integer.parseInt(request.getParameter("bno"));
		int memberkey = Integer.parseInt(request.getParameter("memberkey"));
		
		//싫어요에 정보가 있으면 
		//그거 삭제 후 좋아요 
		//글 좋아요를 누른상태면 
		int badCount = bDao.badCheck(memberkey, bno);
		//그거 삭제 후 싫어요 
		if(badCount == 1) {
			System.out.print("여기들어옴!");
			bDao.badDelete(bno, memberkey);
			bDao.communityGood(request, response, bno, memberkey);
		}
		//싫어요 혹은 좋아요 눌리지 않았다면 . . .
		else if (badCount == 0) {
			System.out.print("else 여기들어옴!");
			bDao.communityGood(request, response, bno, memberkey);
			}
	
}

}
