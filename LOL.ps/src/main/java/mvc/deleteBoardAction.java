package mvc;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yg_ac.dao.BoardDao;

public class deleteBoardAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
			BoardDao bDao = new BoardDao();
			int bno = Integer.parseInt(request.getParameter("bno"));
			bDao.deleteBoard(bno);
			
	}

}
