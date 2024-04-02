package kr.or.ddit.member.servlet11;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.exception.AuthenticateException;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginProcess.do")
public class LoginControllerServlet extends HttpServlet {
	private AuthenticateService service = new AuthenticateServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(memPass);
		
		boolean valid = validate(inputData);
		String logicalViewName = null;
		String message = null;
		HttpSession session = req.getSession();
		if(valid) {
			try{
				MemberVO authMember = service.authenticate(inputData);
				if(session.isNew()) {
					message = "브라우저의 설정 오류, 쿠키 정보를 확인하셈.";
					logicalViewName = "/login/loginForm.jsp";
				}else {
					logicalViewName = "/";
					session.setAttribute("authMember", authMember); //로그인에 성공한 후,
					int maxAge = Optional.ofNullable(req.getParameter("rememberMe")) // remember me 체크박스 여부에 따라 Cookie의 maxAge 셋팅
										.map(rv->60*60*24*3) //3일
										.orElse(0); //0초 = 삭제 (체크박스 선택 안한것)
					Cookie rememberMeCookie = new Cookie("rememberMe", inputData.getMemId()); //Cookie에 저장할 value가 ID
					rememberMeCookie.setMaxAge(maxAge);
					rememberMeCookie.setPath(req.getContextPath());
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
		session.setAttribute("message", message);
		resp.sendRedirect(req.getContextPath() + logicalViewName);
	}

	private boolean validate(MemberVO inputData) {
		boolean valid = true;
		
		//https://commons.apache.org/proper/commons-lang/download_lang.cgi (바이너리 zip파일 다운), String을 null체크 하기위해 더 디테일한 널체킹 유틸들을 제공해주는 StringUtils를 다운받기.
		if(StringUtils.isBlank(inputData.getMemId())) {
			valid = false;
		}
		if(StringUtils.isBlank(inputData.getMemPass())) {
			valid = false;
		}
		return valid;
	}
}












