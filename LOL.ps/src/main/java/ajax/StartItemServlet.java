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

import com.yg_ac.dao.ChampStartItemDao;
import com.yg_ac.dao.Y_DBmanager;
import com.yg_ac.dto.ChampMatchListDto;
import com.yg_ac.dto.ChampStartItemDto;

@WebServlet("/StartItemServlet")
public class StartItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StartItemServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String champion_name = request.getParameter("name");
		String champion_line = request.getParameter("line");
		String select = request.getParameter("select");
		
		Y_DBmanager db = new Y_DBmanager();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		ChampStartItemDao selectData = new ChampStartItemDao();
		if(select.equals("spell")) {
			ArrayList<ChampStartItemDto> spellList = new ArrayList<ChampStartItemDto>();
			spellList = selectData.getSpell(conn, pstmt, rs, champion_name, champion_line);
			
			JSONArray spellArray = new JSONArray();
			for(ChampStartItemDto dto:spellList) {
				JSONObject obj = new JSONObject();
				obj.put("name",dto.getName());
				obj.put("line",dto.getLine());
				obj.put("name1",dto.getName1());
				obj.put("pick1",dto.getPick1());
				obj.put("function1",dto.getFunction1());
				obj.put("name2",dto.getName2());
				obj.put("pick2",dto.getPick2());
				obj.put("function2",dto.getFunction2());
				obj.put("winrate",dto.getWinRate());
				obj.put("pickrate",dto.getPickRate());
				obj.put("count",dto.getCount());
				spellArray.add(obj);
			}
			out.println(spellArray);
		}else if(select.equals("startItem")) {
			ArrayList<ChampStartItemDto> itemList = new ArrayList<ChampStartItemDto>();
			itemList = selectData.getStartItem(conn, pstmt, rs, champion_name, champion_line);
			
			JSONArray itemArray = new JSONArray();
			for(ChampStartItemDto dto:itemList) {
				JSONObject obj = new JSONObject();
				obj.put("name",dto.getName());
				obj.put("line",dto.getLine());
				obj.put("name1",dto.getName1());
				obj.put("pick1",dto.getPick1());
				obj.put("function1",dto.getFunction1());
				obj.put("name2",dto.getName2());
				obj.put("pick2",dto.getPick2());
				obj.put("function2",dto.getFunction2());
				obj.put("winrate",dto.getWinRate());
				obj.put("pickrate",dto.getPickRate());
				obj.put("count",dto.getCount());
				itemArray.add(obj);
			}
			out.println(itemArray);
		}else {
			ArrayList<ChampStartItemDto> shoesList = new ArrayList<ChampStartItemDto>();
			shoesList = selectData.getShoes(conn, pstmt, rs, champion_name, champion_line);
			
			JSONArray shoesArray = new JSONArray();
			for(ChampStartItemDto dto:shoesList) {
				JSONObject obj = new JSONObject();
				obj.put("name",dto.getName());
				obj.put("line",dto.getLine());
				obj.put("name1",dto.getName1());
				obj.put("pick1",dto.getPick1());
				obj.put("function1",dto.getFunction1());
				obj.put("name2",dto.getName2());
				obj.put("pick2",dto.getPick2());
				obj.put("function2",dto.getFunction2());
				obj.put("winrate",dto.getWinRate());
				obj.put("pickrate",dto.getPickRate());
				obj.put("count",dto.getCount());
				shoesArray.add(obj);
			}
			out.println(shoesArray);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
