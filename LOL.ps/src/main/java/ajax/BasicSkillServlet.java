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
import com.yg_ac.dto.ChampBasicSkillDto;

@WebServlet("/BasicSkillServlet")
public class BasicSkillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BasicSkillServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String champion_name = request.getParameter("name");
		
		Y_DBmanager db = new Y_DBmanager();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StatisticsDao basicSkill = new StatisticsDao();
		ArrayList<ChampBasicSkillDto> basicSkillList = new ArrayList<ChampBasicSkillDto>();
		basicSkillList = basicSkill.getChampBasicSkill(conn, pstmt, rs, champion_name);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		JSONArray skillArray = new JSONArray();
		for(ChampBasicSkillDto dto : basicSkillList) {
			JSONObject obj = new JSONObject();
			obj.put("name",dto.getName());
			obj.put("skillname",dto.getSkillName());
			obj.put("skillkey",dto.getSkillKey());
			obj.put("skillfunction",dto.getFunction());
			obj.put("image",dto.getImage());
			skillArray.add(obj);
		}
		out.println(skillArray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
