package mvc;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yg_ac.dao.BoardDao;

public class LikeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		BoardDao bDao = new BoardDao();
//		int bno  = Integer.parseInt(request.getParameter("bno"));
//		int memberkey = Integer.parseInt(request.getParameter("memberkey"));
		
		//싫어요 안누른 상태에서 하면 그냥 좋아요만
//		if(bDao.badCheck(bno , memberkey)) {
//		bDao.communityGood(request, response, bno, memberkey);
		
		}
		//싫어요 눌린상태에서 누르면 좋아요 + 1 에 싫어요 - 1
//		else {
//			bDao.badDelete(bno, memberkey);
//			bDao.communityGood(request, response, bno, memberkey);
//		}
		}
	


