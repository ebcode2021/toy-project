package com.kh.toy.common.mail.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//템플릿 형태로, 인증을 위한 토큰같은 것도 심어놓을 수 있음. input이면 불가능한게, http stream을 통해서 땡겨오면
//서블릿의 객체 + el/jstl 라이브러리 태그들을 그대로 사용할 수 있는 장점이 있다.
@WebServlet("/mail")
public class MailHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public MailHandler() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//mailTemplate 이라는 파라미터로 전송된 template(jsp)로 요청을 재지정
		String template = request.getParameter("mailTemplate");
		request.getRequestDispatcher("/mail-template/" + template).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
