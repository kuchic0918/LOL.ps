package ajax;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yg_ac.dao.MypagePasswordChangeDao;
import com.yg_ac.dao.Y_DBmanager;
import com.yg_ac.dto.MemberDTO;

@WebServlet("/MypagePasswordChangeServlet")
public class MypagePasswordChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nowPW = request.getParameter("now_pw");
		System.out.println(nowPW + 1);
		String newPW= request.getParameter("new_pw");
		System.out.println(newPW + 2);
		HttpSession session = request.getSession(false);
		MemberDTO member = (MemberDTO) session.getAttribute("memberInfo");
		System.out.println(member.getPw() + 3);
		
		Y_DBmanager db = new Y_DBmanager();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		
		MypagePasswordChangeDao mypagePasswordChangeDao = new MypagePasswordChangeDao();
		if(nowPW.equals(member.getPw())) {
			mypagePasswordChangeDao.getMypagePasswordChangeDao(conn, pstmt, member.getMemberkey(), newPW);
			response.sendRedirect("lol/my-page.jsp?password=alright");
		}else {
			response.sendRedirect("lol/my-page.jsp?password=wrong");
		}
	}

}
