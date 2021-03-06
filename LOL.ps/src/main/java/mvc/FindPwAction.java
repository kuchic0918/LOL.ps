package mvc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yg_ac.dao.MemberDAO;

public class FindPwAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		MemberDAO mDao = new MemberDAO();
		if(mDao.getFindPassword(email)!=null) {
			String password = mDao.getFindPassword(email);
			String FROM = "tladytjqoz@naver.com";
			String FROMNAME = "yg_ac";
			String TO = email;
			String SMTP_USERNAME = "tladytjqoz@naver.com";
			String SMTP_PASSWORD = "124tntordytjqT";

			String HOST = "smtp.naver.com";
			int PORT = 587;

			String SUBJECT = "yg_ac에 오신걸 환영합니다! 인증 이메일 입니다!";

			String BODY = String.join(
					System.getProperty("line.separator"),
					"<div style = 'text-align : center; width : 100%; height : 200px; padding : 30px;'>",
					"<h2 style = 'color : black;'>안녕하세요" + email + " 님, 현재 비밀번호는 [ " + password + " ]입니다. 로그인페이지로 돌아가 로그인해주세요.</h2>",
					"<h4 style = 'color : black;'>email에 대한 사용자 활성화를 위하여 아래 링크를 클릭하시고, 로그인하세요.</h4>",
					"<form action='http://localhost:9095/LOL.ps/login.jsp' class='member-form' name='signInForm' method = 'POST' novalidate> ",
					"<button type='submit' name='command' value='login' style = 'border : none; background : #1D2E69; color : #eee; padding : 0px 12px; border-radius : 6px; cursor : pointer;'>로그인</button>",
					"</form>",
					"</br>",
					"</div>"

					);
			Properties props = System.getProperties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.port", PORT); 
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props);
			MimeMessage msg = new MimeMessage(session);
			Transport transport = null;
			try {
				msg.setFrom(new InternetAddress(FROM, FROMNAME));
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
				msg.setSubject(SUBJECT);
				msg.setContent(BODY, "text/html;charset=euc-kr");
				transport = session.getTransport();

				transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
				transport.sendMessage(msg, msg.getAllRecipients());
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					transport.close();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
			request.setAttribute("find", "found");
			request.getRequestDispatcher("findPw.jsp").forward(request, response);
		} else {
			request.setAttribute("find", "notfound");
			request.getRequestDispatcher("findPw.jsp").forward(request, response);
		}
	}
}
