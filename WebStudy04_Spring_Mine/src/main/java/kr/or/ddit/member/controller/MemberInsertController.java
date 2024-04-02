package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원 가입 처리
 * 1. 가입 양식 제공(GET)
 * 2. 양식을 통해 입력 및 전송된 데이터 처리(POST)
 *
 */
@Slf4j
@Controller
@RequestMapping("/member/memberInsert.do")
public class MemberInsertController {
	
	@Inject
	private MemberService service;
	
	@ModelAttribute("member") //Get핸들러와, Post핸들러 두개에서 이 모델이 필요한데, 중복되는 코드이므로 메서드위에 @ModelAttribute를 설정해준다.
							//그럼 2개의 핸들러메서드를 실행하기 전에 이걸 가장먼저 실행해줌. jsp의 form태그 - modelAttribute에서 부터 반영된 이름.
	public MemberVO member() {
		return new MemberVO();
	}
	
	@GetMapping
	protected String insertForm()  {
		return "member/memberForm";
	}
	
	@PostMapping
	protected String doPost(
			@Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member
			, BindingResult errors
			, Model model
	) {

		String logicalViewName = null;
		if(! errors.hasErrors()) {
	//		3. 가입 로직 호출
				ServiceResult result = service.createMember(member);
	//		4. 로직의 실행 결과에 따른 뷰 선택
				String message = null;
				switch (result) {
				case OK:
					logicalViewName = "redirect:/"; // Post-Redirect-Get
					break;
				case FAIL:
					logicalViewName = "member/memberForm";
					break;
					
				default: // 아이디 중복
					logicalViewName = "member/memberForm";
					message = "아이디 중복, 바꾸셈;";
					break;
				}
				
				model.addAttribute("message", message);
		}else {
			logicalViewName = "member/memberForm";
		}
		
		return logicalViewName;
	}

}



















