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

import com.yg_ac.dao.CoreCombineDao;
import com.yg_ac.dao.Y_DBmanager;
import com.yg_ac.dto.CoreCombineDto;

@WebServlet("/CoreCombineServlet")
public class CoreCombineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CoreCombineServlet() {
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
		
		CoreCombineDao selectData = new CoreCombineDao();
		if(select.equals("2core")) {
			ArrayList<CoreCombineDto> coreList = new ArrayList<CoreCombineDto>();
			coreList = selectData.get2CoreCombine(conn, pstmt, rs, champion_name, champion_line);
			
			JSONArray coreArray = new JSONArray();
			for(CoreCombineDto dto : coreList) {
				JSONObject obj = new JSONObject();
				obj.put("pick1",dto.getPick1());
				obj.put("image1",dto.getImage1());
				obj.put("function1",dto.getFunction1());
				
				obj.put("pick2",dto.getPick2());
				obj.put("image2",dto.getImage2());
				obj.put("function2",dto.getFunction2());
				
				obj.put("winrate",dto.getWinRate());
				obj.put("pickrate",dto.getPickRate());
				obj.put("count",dto.getCount());
				coreArray.add(obj);
			}
			out.println(coreArray);
		}else if(select.equals("3core")) {
			ArrayList<CoreCombineDto> coreList = new ArrayList<CoreCombineDto>();
			coreList = selectData.get3CoreCombine(conn, pstmt, rs, champion_name, champion_line);
			
			JSONArray coreArray = new JSONArray();
			for(CoreCombineDto dto : coreList) {
				JSONObject obj = new JSONObject();
				obj.put("pick1",dto.getPick1());
				obj.put("image1",dto.getImage1());
				obj.put("function1",dto.getFunction1());
				
				obj.put("pick2",dto.getPick2());
				obj.put("image2",dto.getImage2());
				obj.put("function2",dto.getFunction2());
				
				obj.put("pick3",dto.getPick3());
				obj.put("image3",dto.getImage3());
				obj.put("function3",dto.getFunction3());
				
				obj.put("winrate",dto.getWinRate());
				obj.put("pickrate",dto.getPickRate());
				obj.put("count",dto.getCount());
				coreArray.add(obj);
			}
			out.println(coreArray);
		}else {
			ArrayList<CoreCombineDto> coreList = new ArrayList<CoreCombineDto>();
			coreList = selectData.get4CoreCombine(conn, pstmt, rs, champion_name, champion_line);
			
			JSONArray coreArray = new JSONArray();
			for(CoreCombineDto dto : coreList) {
				JSONObject obj = new JSONObject();
				obj.put("pick1",dto.getPick1());
				obj.put("image1",dto.getImage1());
				obj.put("function1",dto.getFunction1());
				
				obj.put("pick2",dto.getPick2());
				obj.put("image2",dto.getImage2());
				obj.put("function2",dto.getFunction2());
				
				obj.put("pick3",dto.getPick3());
				obj.put("image3",dto.getImage3());
				obj.put("function3",dto.getFunction3());
				
				obj.put("pick4",dto.getPick4());
				obj.put("image4",dto.getImage4());
				obj.put("function4",dto.getFunction4());
				
				obj.put("winrate",dto.getWinRate());
				obj.put("pickrate",dto.getPickRate());
				obj.put("count",dto.getCount());
				coreArray.add(obj);
			}
			out.println(coreArray);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
