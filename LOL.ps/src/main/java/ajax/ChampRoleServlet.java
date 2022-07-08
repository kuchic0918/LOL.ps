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

import com.yg_ac.dao.ChampRoleDao;
import com.yg_ac.dao.Y_DBmanager;
import com.yg_ac.dto.ChampRoleDto;

@WebServlet("/ChampRoleServlet")
public class ChampRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChampRoleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String champion_name = request.getParameter("name");
		
		Y_DBmanager db = new Y_DBmanager();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ChampRoleDao champRole = new ChampRoleDao();
		ArrayList<ChampRoleDto> champRoleList = new ArrayList<ChampRoleDto>();
		champRoleList = champRole.getChampRole(conn, pstmt, rs, champion_name);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		JSONArray roleArray = new JSONArray();
		for(ChampRoleDto dto:champRoleList) {
			JSONObject obj = new JSONObject();
			obj.put("role1",dto.getRole1());
			obj.put("role2",dto.getRole2());
			roleArray.add(obj);
		}
		out.println(roleArray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
