package mvc;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yg_ac.dao.MemberDAO;

public class FindIdAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		MemberDAO mDao = new MemberDAO();
		if(mDao.getFindEmail(email)==0) {
			request.setAttribute("find", "notfound");
			request.getRequestDispatcher("findId.jsp").forward(request, response);
		}else {
			request.setAttribute("find", "found");
			request.getRequestDispatcher("findId.jsp").forward(request, response);
		}
	}
}
