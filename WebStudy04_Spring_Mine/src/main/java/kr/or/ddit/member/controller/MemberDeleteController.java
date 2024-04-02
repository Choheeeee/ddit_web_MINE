package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

// POJO(PlainOldJavaObject)


@Controller
public class MemberDeleteController {
	
	@Inject
	private MemberService service;
	
	@PostMapping(("/member/memberDelete.do"))
	public String doPost(Principal principal, String password, RedirectAttributes redirectAttributes) {
		
//		HttpSession session = req.getSession();
		String memId = principal.getName();
		MemberVO inputData = new MemberVO(memId, password);
		ServiceResult result = service.removeMember(inputData);
		String logicalViewName = null;
		String message = null;
		switch (result) {
		case INVALIDPASSWORD:
			message = "비밀 번호 오류";
			logicalViewName = "redirect:/mypage";
			redirectAttributes.addFlashAttribute("message", message);
			break;
		case OK:
//			session.invalidate(); //로그아웃 시키기위해 session스코프 이용 but LogOut컨트롤러에서 이미 로그아웃 로직을 갖고있음. 근데 LogOut컨트롤러는 PostMapping임 => 그래서 여기서 발생하는 post요청 그대로 유지한채로 LogOutController까지 가야하므로, 리다이랙트가 아닌 forward여야함.
//			logicalViewName = "redirect:/";
			
			logicalViewName = "forward:/login/logOut.do";
			break;
		default:
			message = "서버 오류, 쫌따 다시 탈퇴하셈.";
			logicalViewName = "redirect:/mypage";
			redirectAttributes.addFlashAttribute("message", message);
			break;
		}
		
		return logicalViewName;
	}
}























