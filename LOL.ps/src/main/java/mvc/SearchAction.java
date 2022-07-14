package mvc;

import java.io.IOException;	

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yg_ac.dao.StatisticsDao;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StatisticsDao dao = new StatisticsDao();
		
		String name = request.getParameter("name");
		String line = dao.getChampionHighLine(name);
		request.setAttribute("name", name);
		request.setAttribute("line", line);
		
		request.getRequestDispatcher("Controller?command=statistics").forward(request, response);
	}
}
