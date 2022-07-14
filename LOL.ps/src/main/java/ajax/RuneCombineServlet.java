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
import com.yg_ac.dto.RuneCombineDto;

@WebServlet("/RuneCombineServlet")
public class RuneCombineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RuneCombineServlet() {
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
		ArrayList<RuneCombineDto> runeCombineList = new ArrayList<RuneCombineDto>();
		runeCombineList = selectData.getRuneCombine(champion_name, champion_line);
		
		JSONArray runeCombineArray = new JSONArray();
		for(RuneCombineDto dto : runeCombineList) {
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
			
			obj.put("pick5",dto.getPick5());
			obj.put("image5",dto.getImage5());
			obj.put("function5",dto.getFunction5());
			
			obj.put("pick6",dto.getPick6());
			obj.put("image6",dto.getImage6());
			obj.put("function6",dto.getFunction6());
			
			obj.put("winrate",dto.getWinRate());
			obj.put("pickrate",dto.getPickRate());
			runeCombineArray.add(obj);
		}
		out.println(runeCombineArray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
