package mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CommunitySearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("titleWriter");
		String category = request.getParameter("category");
		String search = request.getParameter("searchWrite");
		System.out.println(category + " " + name + " " + search);
		if("title".equals(name)) {
			request.setAttribute("titleWriter", name);
			request.setAttribute("searchWrite", search);
			request.setAttribute("search", "title");
			request.setAttribute("category", category);
			request.getRequestDispatcher("community.jsp").forward(request, response);
		}else {
			request.setAttribute("titleWriter", name);
			request.setAttribute("search", "writer");
			request.getRequestDispatcher("community.jsp").forward(request, response);
		}
	}
}