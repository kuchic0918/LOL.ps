package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yg_ac.dao.MemberDAO;
import com.yg_ac.dao.Y_DBmanager;
import com.yg_ac.dto.MemberDTO;


@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SignInServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Y_DBmanager y_dbmanager = new Y_DBmanager();
	 	Connection conn  = y_dbmanager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDAO memberdao = new MemberDAO();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		MemberDTO member = new MemberDTO(0 , email ,password , nickname , null, null, null);
		try {
			if(memberdao.emailisOverlap(email,pstmt, conn,rs)) {
				response.sendRedirect("lol/signin.jsp");
		
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(memberdao.NickNameIsOverlap(nickname, pstmt,  conn, rs)) {
				response.sendRedirect("lol/signin.jsp");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//email pw,  하고 닉네임이 중복되지 않으면 ...
		if(memberdao.isVaildEmail(email, conn, pstmt, rs) == false && memberdao.NickNameIsOverlap(nickname, pstmt, conn, rs) == false) {
			memberdao.signin(member, conn, pstmt);
			response.sendRedirect("lol/login.jsp");
			}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
