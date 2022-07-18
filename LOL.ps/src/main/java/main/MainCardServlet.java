package main;

import java.io.IOException;		
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.yg_ac.dao.MainCardDao;
import com.yg_ac.dto.MainCardDto;

@WebServlet("/MainCardServlet")
public class MainCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String champion_name = request.getParameter("name");
		String champion_line = request.getParameter("line");
		MainCardDto card = null;
		
		MainCardDao mainCardDao = new MainCardDao();
		try {
			card = mainCardDao.getMainCard(champion_name, champion_line);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.setContentType("application/json; charset=utf-8"); 
		PrintWriter out = response.getWriter();			
		JSONObject obj = new JSONObject();	
	
		obj.put("name", card.getName());
		obj.put("line", card.getLine());
		obj.put("winRate", card.getWin());
		obj.put("winRateBefore", card.getWinBefore());
		obj.put("winRateVari", card.getWinVari());
		obj.put("pickRate", card.getPick());
		obj.put("pickRateBefore", card.getPickBefore());
		obj.put("pickRateVari", card.getPickVari());
		obj.put("banRate", card.getBan());
		obj.put("banRateBefore", card.getBanBefore());
		obj.put("banRateVari", card.getBanVari());
		obj.put("image", card.getImage());
		response.getWriter().print(obj);
		
		
	}
}