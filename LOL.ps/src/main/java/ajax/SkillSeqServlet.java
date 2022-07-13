package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.yg_ac.dao.SkillSeqDao;
import com.yg_ac.dao.Y_DBmanager;
import com.yg_ac.dto.SkillSeqDto;

@WebServlet("/SkillSeqServlet")
public class SkillSeqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SkillSeqServlet() {
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
		
		SkillSeqDao selectData = new SkillSeqDao();
		if(select.equals("3")) {
			ArrayList<SkillSeqDto> seqList = new ArrayList<SkillSeqDto>();
			seqList = selectData.getSkillSeq3(conn, pstmt, rs, champion_name, champion_line);
			
			JSONArray seqArray = new JSONArray();
			for(SkillSeqDto dto : seqList) {
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
				seqArray.add(obj);
			}
			out.println(seqArray);
		}else if(select.equals("6")) {
			ArrayList<SkillSeqDto> seqList = new ArrayList<SkillSeqDto>();
			seqList = selectData.getSkillSeq6(conn, pstmt, rs, champion_name, champion_line);
			
			JSONArray seqArray = new JSONArray();
			for(SkillSeqDto dto : seqList) {
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
				obj.put("count",dto.getCount());
				seqArray.add(obj);
			}
			out.println(seqArray);
		}else {
			ArrayList<SkillSeqDto> seqList = new ArrayList<SkillSeqDto>();
			seqList = selectData.getSkillSeq11(conn, pstmt, rs, champion_name, champion_line);
			
			JSONArray seqArray = new JSONArray();
			for(SkillSeqDto dto : seqList) {
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
				
				obj.put("pick7",dto.getPick7());
				obj.put("image7",dto.getImage7());
				obj.put("function7",dto.getFunction7());
				
				obj.put("pick8",dto.getPick8());
				obj.put("image8",dto.getImage8());
				obj.put("function8",dto.getFunction8());
				
				obj.put("pick9",dto.getPick9());
				obj.put("image9",dto.getImage9());
				obj.put("function9",dto.getFunction9());
				
				obj.put("pick10",dto.getPick10());
				obj.put("image10",dto.getImage10());
				obj.put("function10",dto.getFunction10());
				
				obj.put("pick11",dto.getPick11());
				obj.put("image11",dto.getImage11());
				obj.put("function11",dto.getFunction11());
				
				obj.put("winrate",dto.getWinRate());
				obj.put("pickrate",dto.getPickRate());
				obj.put("count",dto.getCount());
				seqArray.add(obj);
			}
			out.println(seqArray);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
