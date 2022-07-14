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
import com.yg_ac.dto.RuneShardDto;

@WebServlet("/RuneShardServlet")
public class RuneShardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RuneShardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String champion_name = request.getParameter("name");
		String champion_line = request.getParameter("line");
		
		Y_DBmanager db = new Y_DBmanager();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		StatisticsDao selectData = new StatisticsDao();
		ArrayList<RuneShardDto> runeShardList = new ArrayList<RuneShardDto>();
		runeShardList = selectData.getRuneShard(conn, pstmt, rs, champion_name, champion_line);
		
		JSONArray runeShardArray = new JSONArray();
		for(RuneShardDto dto : runeShardList) {
			JSONObject obj = new JSONObject();
			obj.put("pick1",dto.getPick1());
			obj.put("image1",dto.getImage1());
			
			obj.put("pick2",dto.getPick2());
			obj.put("image2",dto.getImage2());
			
			obj.put("pick3",dto.getPick3());
			obj.put("image3",dto.getImage3());
			
			obj.put("winrate",dto.getWinRate());
			obj.put("pickrate",dto.getPickRate());
			runeShardArray.add(obj);
		}
		out.println(runeShardArray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
