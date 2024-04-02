package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;


@Controller
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateController {
	
	@Inject
	private MemberService service;
	
	@GetMapping
	//프린시펄을 핸들러어뎁터로부터 받으려면, principal화 된 인증객체(=memberVO)가 필요한데, 이게 제너레이터 필터를 통해서 이루어짐.
	public String updateForm(Principal principal, Model model) { //wrapping된 memberVO를 갖고있음.
		MemberVO member = service.retrieveMember(principal.getName());
		model.addAttribute("member", member);
		return "member/memberEdit";
	}
	
	@PostMapping
	public String updateProcess(
			@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member //위에서 검증이 된 멤버객체
			, BindingResult errors
			, Principal principal //memId를 updateForm에서 수정할 필요가 없어서 안받고 있는데, 이걸 authMember에서 꺼내고 있으므로, Principal객체가 필요함.
			, Model model
	) {
		member.setMemId(principal.getName());
		
	
		String logicalViewName = null;
		if(! errors.hasErrors()) {
	//		3. 수정 로직 호출
				ServiceResult result = service.modifyMember(member);
	//		4. 로직의 실행 결과에 따른 뷰 선택
				String message = null;
				switch (result) {
				case INVALIDPASSWORD:
					logicalViewName = "member/memberEdit";
					message = "인증 실패";
					break;
				case OK:
					logicalViewName = "redirect:/mypage";
					break;
					
				default:
					logicalViewName = "member/memberEdit";
					message = "서버 오류";
					break;
				}
				
				model.addAttribute("message", message);
		}else {
			logicalViewName = "member/memberEdit";
		}
		
		return logicalViewName;
		
	}
}
