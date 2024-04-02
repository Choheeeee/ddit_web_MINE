package kr.or.ddit.member.controller.ajax;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.paging.BootstrapFormBasePaginationRenderer;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.PaginationRenderer;
import kr.or.ddit.common.paging.SearchCondition;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

//클라이언트는 get요청을 보내므로 get핸들러를 탐. (js 필요)타일즈의 postScript와 preScript가 작동해야하므로 데피니션이 작동해야함.


@Controller
@RequestMapping("/member/ajax")
public class MemberListAjaxController {
	@Inject
	private MemberService service;
	
	@GetMapping("memberList.do")
	public String listUI() { //UI로 연결만하는 유일한 동기요청
		return "member/ajax/memberList";
	}
	
	
	
	@GetMapping(value = "memberList.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public void listData( //여기는 데이터만 서비스함 => JSON 필요 (contentNegotiator가 작동하므로, Accept헤더 조절)
			@ModelAttribute("paging") PaginationInfo paging
//			, @ModelAttribute("condition") SearchCondition simpleCondition
			, @RequestParam(name="page", required = false, defaultValue = "1") int currentPage	//기본값을 설정할 필요가 없다면, 디폴트벨류대신 옵셔널객체도 가능.
			, Model model //reqest 스코프에 넣으줄 모델
		) {
	
//		PaginationInfo paging = new PaginationInfo(3,3);
		paging.setCurrentPage(currentPage);
//		paging.setSimpleCondition(simpleCondition);
		List<MemberVO> memberList = service.retrieveMemberList(paging);
		
		
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#submitForm");
		
		String pagingHTML = renderer.renderPagination(paging);
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("pagingHTML", pagingHTML);
		
		
	}
	
	@GetMapping(value = "{propertyName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void oneData(
			Model model
			, @PathVariable String memId
			) {
		
		MemberVO member = service.retrieveMember(memId);
		model.addAttribute("member", member);
	}
	
	
	//응답을 마샬링해서 내보내는 방법 @ResponseBody를 쓰거나, 반환타입에 String을 주고 "jsonView"를 리턴하면 됨!!
	@ResponseBody
	@GetMapping(value = "memberListResponseBody.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public void listData2( //여기는 데이터만 서비스함 => JSON 필요 (contentNegotiator가 작동하므로, Accept헤더 조절)
			@ModelAttribute("paging") PaginationInfo paging
//			, @ModelAttribute("condition") SearchCondition simpleCondition
			, @RequestParam(name="page", required = false, defaultValue = "1") int currentPage	//기본값을 설정할 필요가 없다면, 디폴트벨류대신 옵셔널객체도 가능.
			, Model model //reqest 스코프에 넣으줄 모델
			) {
		
//		PaginationInfo paging = new PaginationInfo(3,3);
		paging.setCurrentPage(currentPage);
//		paging.setSimpleCondition(simpleCondition);
		List<MemberVO> memberList = service.retrieveMemberList(paging);
		
		
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#submitForm");
		
		String pagingHTML = renderer.renderPagination(paging);
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("pagingHTML", pagingHTML);
		
	}
}