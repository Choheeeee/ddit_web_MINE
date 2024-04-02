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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/mypage")
public class MypageControllerServlet{
	
	@Inject
	private MemberService service;
	
	@GetMapping
	protected String mypage(Principal principal, Model model) {
		MemberVO member = service.retrieveMember(principal.getName());
		model.addAttribute("member", member);
		
		 return "member/mypage";
		
	}
}