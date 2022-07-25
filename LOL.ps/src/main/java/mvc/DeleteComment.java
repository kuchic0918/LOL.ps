package mvc;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yg_ac.dao.BoardDao;

public class DeleteComment implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		BoardDao bDao = new BoardDao();	
		int cno = Integer.parseInt(request.getParameter("cno"));
		System.out.println(cno);
		bDao.DeleteComment(cno);
	}

}
