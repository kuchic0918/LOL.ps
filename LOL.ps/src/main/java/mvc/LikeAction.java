package mvc;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yg_ac.dao.BoardDao;

public class LikeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		BoardDao bDao = new BoardDao();
		int bno  = Integer.parseInt(request.getParameter("bno"));
		int memberkey = Integer.parseInt(request.getParameter("memberkey"));
		
		if(bDao.badCheck(bno , memberkey)) {
		bDao.communityGood(request, response, bno, memberkey);
		
		}
		else {
			bDao.communityBad(request, response, bno, memberkey);
		}
		}
	}


