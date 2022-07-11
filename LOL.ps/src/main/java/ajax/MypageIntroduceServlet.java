package ajax;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yg_ac.dao.MypageIntroduceDao;
import com.yg_ac.dto.MemberDTO;

@WebServlet("/MypageIntroduceServlet")
public class MypageIntroduceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDTO member = (MemberDTO) request.getAttribute("memberInfo");
		String introduce = request.getParameter("mypage-textarea");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		MypageIntroduceDao mypageIntroduceDao = new MypageIntroduceDao();
		mypageIntroduceDao.updateMypageIntroduce(conn, pstmt, member.getMemberkey(), introduce);
	}
}
