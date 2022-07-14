package main;

import java.io.IOException;	
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yg_ac.dao.StatisticsDao;
import com.yg_ac.dao.Y_DBmanager;
import com.yg_ac.dto.ChampionHighLineDto;

@WebServlet("/MainStatisticsServlet")
public class MainStatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Y_DBmanager db = new Y_DBmanager();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String champion_name = request.getParameter("name");
		String line = null;
		StatisticsDao championHighLlineDao = new StatisticsDao();
		line = championHighLlineDao.getChampionHighLine(champion_name);
		
		if(line!=null) {
			response.sendRedirect("lol/statistics.jsp?name=" + URLEncoder.encode(champion_name, "utf-8") + "&line=" + URLEncoder.encode(line, "utf-8"));
		} else {
			response.sendRedirect("lol/main.jsp?searchedChampion=wrong");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
