package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.WebSession;
import org.springframework.web.server.session.CookieWebSessionIdResolver;
import org.springframework.web.server.session.WebSessionIdResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.member.exception.AuthenticateException;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.LoginGroup;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;


@Controller
@RequestMapping("/login/loginProcess.do")
public class LoginController {
	
	@Inject
	private AuthenticateService service;
	
	@Inject
	private WebApplicationContext context; //웹이니까 하위컨테이너 컨텍스트가 들어옴.
	private String contextPath;
	
	@PostConstruct
	public void init() {
		contextPath = context.getServletContext().getContextPath();	//톰캣과 스프링컨테이너 총 2개가 있는데, 이 컨테이너들은 상호간의 레퍼런스를 받을 수 있음.
	}
	
	@PostMapping
	protected String loginProcess(		
			@Validated(LoginGroup.class) MemberVO inputData
			, BindingResult errors
			, HttpSession session
			, Optional<String> rememberMe
			, HttpServletResponse resp
			, RedirectAttributes redirectAttributes
			) {
		
		boolean valid = !errors.hasErrors();
		String logicalViewName = null;
		String message = null;
		if(valid) {
			try{
				MemberVO authMember = service.authenticate(inputData);
				if(session.isNew()) {
					message = "브라우저의 설정 오류, 쿠키 정보를 확인하셈.";
					logicalViewName = "/login/loginForm.jsp";
				}else {
					logicalViewName = "/";
					session.setAttribute("authMember", authMember);
					int maxAge = rememberMe
										.map(rv->60*60*24*3)
										.orElse(0);
					Cookie rememberMeCookie = new Cookie("rememberMe", inputData.getMemId());
					rememberMeCookie.setMaxAge(maxAge);
					rememberMeCookie.setPath(contextPath);
					resp.addCookie(rememberMeCookie);
				}
			}catch(AuthenticateException e){
				message = "아이디나 비밀번호 오류";
				logicalViewName = "/login/loginForm.jsp";
			}	
		}else {
//			resp.sendError(400);
			message = "아이디나 비밀번호 누락";
			logicalViewName = "/login/loginForm.jsp";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:" + logicalViewName;
	}

}