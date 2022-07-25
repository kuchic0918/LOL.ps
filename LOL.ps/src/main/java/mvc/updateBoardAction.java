package mvc;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yg_ac.dao.BoardDao;

public class updateBoardAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, SQLException {
		response.setCharacterEncoding("UTF-8");
		BoardDao bDao = new BoardDao();
		String title = request.getParameter("writeTitle");
		String content = request.getParameter("editordata");
		int bno = Integer.parseInt(request.getParameter("bno"));
		String category = request.getParameter("category");
		bDao.updateBoard(bno, title, content);
		response.sendRedirect("community.jsp?category="+URLEncoder.encode(category, "UTF-8"));
		
		
			
	}

}
