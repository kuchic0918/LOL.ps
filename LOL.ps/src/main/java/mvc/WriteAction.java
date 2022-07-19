package mvc;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yg_ac.dao.BoardDao;
import com.yg_ac.dto.MemberDTO;

public class WriteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao bDao = new BoardDao();
		HttpSession session = request.getSession(false);
		MemberDTO member = (MemberDTO)session.getAttribute("memberInfo");
		int memberkey = member.getMemberkey();
		String title = request.getParameter("writeTitle");
		String content = request.getParameter("editordata");
		String category = request.getParameter("category");
		String champName = request.getParameter("champion");
		
		bDao.writeAction(memberkey, title, content, category, champName);
		response.sendRedirect("community.jsp?category="+URLEncoder.encode(category, "UTF-8"));
	}

}
