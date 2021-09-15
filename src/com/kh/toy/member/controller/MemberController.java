package com.kh.toy.member.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.DataAccessException;
import com.kh.toy.common.exception.HandlableException;
import com.kh.toy.common.exception.PageNotFoundException;
import com.kh.toy.member.model.dto.Member;
import com.kh.toy.member.model.service.MemberService;


@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private MemberService memberService = new MemberService();
	
    public MemberController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length-1]) {
		case "login-form":
			loginForm(request,response);
			break;
		case "login":
			login(request,response);
			break;
		case "logout":
			logout(request,response);
			break;
		case "join-form":
			joinForm(request,response);
			break;
		case "join":
			join(request,response);
			break;
		case "id-check":
			checkID(request,response);
			break;
		case "join-impl":
			joinImpl(request,response);
			break;
		case "mypage" :
			mypage(request, response);
			break;
		default: throw new PageNotFoundException();
		}
	}

	

	private void mypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/mypage").forward(request, response);
		
	}

	private void joinImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		
		
		Member member = (Member)session.getAttribute("persistUser");
		memberService.insertMember(member);
		
		session.removeAttribute("persistToken");
		session.removeAttribute("persistUser");
		
		response.sendRedirect("/member/login-form");
		
	}

	private void checkID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		
		Member member = memberService.selectMemberById(userId);
		
		if(member==null) {
			//우리는 브라우저랑 통신하는게 아니라, fetch와 하기때문에 forward나 redirect를 쓸 필요는 없다.
			response.getWriter().print("available");
		}else {
			response.getWriter().print("disable");
		}
		
	}

	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String tell = request.getParameter("tell");
		String email = request.getParameter("email");
		
		Member member = new Member();
		member.setUserId(userId);
		member.setPassword(password);
		member.setTell(tell);
		member.setEmail(email);
		
		String persistToken = UUID.randomUUID().toString(); //중복될 확률이 번개를 3000번 맞을 확률보다 낮음
		request.getSession().setAttribute("persistUser", member);
		request.getSession().setAttribute("persistToken", persistToken);
		
		memberService.authenticateByEmail(member, persistToken);
		request.setAttribute("msg", "이메일이 발송되었습니다.");
		request.setAttribute("url", "/index");
		request.getRequestDispatcher("/error/result").forward(request, response);
	}

	private void joinForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/member/join-form").forward(request, response);
		
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 : 사용자 인증정보가 담기는 공간 x
		//		session scope를 가지는 저장공간
		request.getSession().removeAttribute("authentication");
		response.sendRedirect("/index");
		
	}

	private void loginForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.getRequestDispatcher("/member/login").forward(request, response);
		
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		Member member = null;
		
		//1.DataBase 또는 Service 단에서 문제가 생겨서 예외가 발생하는 경우 => service 단에서 처리
		
			member = memberService.memberAuthenticate(userId, password);
		
		
		//2.사용자가 잘못된 아이디와 비밀번호를 입력한 경우
		//사용자에게 아이디나 비밀번호가 틀렸음을 알림, login-form으로 redirect
		if(member==null) {
			response.sendRedirect("/member/login-form?err=1");
			return;
		}
		
		//만약 아이디를 잘 입력했으면 session에 담아두기
		request.getSession().setAttribute("authentication", member);
		response.sendRedirect("/index");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
