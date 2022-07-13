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

import com.yg_ac.dao.ChampBasicStatDao;
import com.yg_ac.dao.Y_DBmanager;
import com.yg_ac.dto.ChampBasicStatDto;
import com.yg_ac.dto.ChampMatchListDto;

@WebServlet("/BasicInfoServlet")
public class BasicInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BasicInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String champion_name = request.getParameter("name");
		
		Y_DBmanager db = new Y_DBmanager();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ChampBasicStatDao basicStat = new ChampBasicStatDao();
		ArrayList<ChampBasicStatDto> basicStatList = new ArrayList<ChampBasicStatDto>();
		basicStatList = basicStat.getChampBasicStat(conn, pstmt, rs, champion_name);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		JSONArray statArray = new JSONArray();
		for(ChampBasicStatDto dto : basicStatList) {
			JSONObject obj = new JSONObject();
			obj.put("name",dto.getName());
			obj.put("stat",dto.getStat());
			obj.put("start",dto.getStatStart());
			obj.put("finalstat",dto.getStatFinal());
			obj.put("rank",dto.getStatRank());
			statArray.add(obj);
		}
		out.println(statArray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
