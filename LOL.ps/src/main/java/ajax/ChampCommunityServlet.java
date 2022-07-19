package ajax;

import java.io.IOException;	
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.yg_ac.dao.BoardDao;
import com.yg_ac.dao.StatisticsDao;
import com.yg_ac.dto.BoardDto;
import com.yg_ac.dto.MemberDTO;

@WebServlet("/ChampCommunityServlet")
public class ChampCommunityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String champion_name = request.getParameter("name");
		StatisticsDao sDao = new StatisticsDao();
		BoardDao bDao = new BoardDao();
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		String image = bDao.getImage(champion_name);
		list = sDao.getBoardList(champion_name);
		MemberDTO writer = null;
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		JSONArray champCommynityArray = new JSONArray();
		for(BoardDto dto:list) {
			JSONObject obj = new JSONObject();
			int like = dto.getGood() - dto.getBad();
			if(like<0) {like = 0;}
			writer = bDao.getWriter(dto.getMemberkey());
			obj.put("writer", writer.getNickname());
			obj.put("bno", dto.getBno());
			obj.put("image", image);
			obj.put("champname", dto.getChampName());
			obj.put("title", dto.getTitle());
			obj.put("content",dto.getContent());
			obj.put("writedate", dto.getWritedate());
			obj.put("like", like);
			obj.put("count", dto.getCount());
			champCommynityArray.add(obj);
		}
		out.println(champCommynityArray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
