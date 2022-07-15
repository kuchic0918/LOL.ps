package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yg_ac.dao.MemberDAO;
import com.yg_ac.dto.MemberDTO;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDAO mDao = new MemberDAO();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		if (mDao.loginvalid(email, password)) {
			MemberDTO member = mDao.findByEmailPwMemberInfo(email, password);	 	
			session.setAttribute("memberInfo", member);
			request.getRequestDispatcher("my-page.jsp").forward(request, response);
			
		} else if (mDao.isVaildEmail(email) == false
				&& mDao.isVaildPW(password) == false) {
			response.sendRedirect("login.jsp");
			
		} else if (mDao.isVaildPW(password) == false) {
			response.sendRedirect("login.jsp");
			
		} else if (mDao.isVaildEmail(email) == false) {
			response.sendRedirect("login.jsp");
			
		}
	}

}
