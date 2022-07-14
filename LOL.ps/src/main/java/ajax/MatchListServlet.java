package ajax;

import java.io.IOException;	
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.yg_ac.dao.StatisticsDao;
import com.yg_ac.dao.Y_DBmanager;
import com.yg_ac.dto.ChampMatchListDto;

@WebServlet("/MatchListServlet")
public class MatchListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MatchListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String champion_name = request.getParameter("name");
		String champion_line = request.getParameter("line");
		String counter = request.getParameter("counter");
		
		Y_DBmanager db = new Y_DBmanager();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StatisticsDao match = new StatisticsDao();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		if(counter.equals("hard")) {
		ArrayList<ChampMatchListDto> matchHardList = new ArrayList<ChampMatchListDto>();
		matchHardList = match.getChampMatchListHard(champion_name, champion_line);
		
		JSONArray hardArray = new JSONArray();
		for(ChampMatchListDto dto:matchHardList) {
			JSONObject obj = new JSONObject();
			obj.put("name",dto.getName());
			obj.put("line",dto.getLine());
			obj.put("count",dto.getCount());
			obj.put("winrate",dto.getWinRate());
			obj.put("image",dto.getImage());
			hardArray.add(obj);
		}
		out.println(hardArray);
		}else{
			ArrayList<ChampMatchListDto> matchEasyList = new ArrayList<ChampMatchListDto>();
			matchEasyList = match.getChampMatchListEasy(champion_name, champion_line);
			
			
			JSONArray easyArray = new JSONArray();
			for(ChampMatchListDto dto:matchEasyList) {
				JSONObject obj = new JSONObject();
				obj.put("name",dto.getName());
				obj.put("line",dto.getLine());
				obj.put("count",dto.getCount());
				obj.put("winrate",dto.getWinRate());
				obj.put("image",dto.getImage());
				easyArray.add(obj);
			}
			out.println(easyArray);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
