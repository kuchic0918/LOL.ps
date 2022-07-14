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
import com.yg_ac.dto.CoreEachDto;

@WebServlet("/CoreEachServlet")
public class CoreEachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CoreEachServlet() {
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
		
		StatisticsDao selectData = new StatisticsDao();
		if(select.equals("first")) {
			ArrayList<CoreEachDto> firstList = new ArrayList<CoreEachDto>();
			firstList = selectData.getCore1(conn, pstmt, rs, champion_name, champion_line);
			
			JSONArray firstArray = new JSONArray();
			for(CoreEachDto dto : firstList) {
				JSONObject obj = new JSONObject();
				obj.put("pick",dto.getPick());
				obj.put("image",dto.getImage());
				obj.put("function1",dto.getFunction());
				obj.put("winrate",dto.getWinRate());
				obj.put("pickrate",dto.getPickRate());
				obj.put("count",dto.getCount());
				firstArray.add(obj);
			}
			out.println(firstArray);
		}else if(select.equals("secend")) {
			ArrayList<CoreEachDto> secendList = new ArrayList<CoreEachDto>();
			secendList = selectData.getCore2(conn, pstmt, rs, champion_name, champion_line);
			
			JSONArray secendArray = new JSONArray();
			for(CoreEachDto dto : secendList) {
				JSONObject obj = new JSONObject();
				obj.put("pick",dto.getPick());
				obj.put("image",dto.getImage());
				obj.put("function2",dto.getFunction());
				obj.put("winrate",dto.getWinRate());
				obj.put("pickrate",dto.getPickRate());
				obj.put("count",dto.getCount());
				secendArray.add(obj);
			}
			out.println(secendArray);
		}else {
			ArrayList<CoreEachDto> therdList = new ArrayList<CoreEachDto>();
			therdList = selectData.getCore3(conn, pstmt, rs, champion_name, champion_line);
			
			JSONArray therdArray = new JSONArray();
			for(CoreEachDto dto : therdList) {
				JSONObject obj = new JSONObject();
				obj.put("pick",dto.getPick());
				obj.put("image",dto.getImage());
				obj.put("function3",dto.getFunction());
				obj.put("winrate",dto.getWinRate());
				obj.put("pickrate",dto.getPickRate());
				obj.put("count",dto.getCount());
				therdArray.add(obj);
			}
			out.println(therdArray);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
