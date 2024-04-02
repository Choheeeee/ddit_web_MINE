package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.paging.BootstrapFormBasePaginationRenderer;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.PaginationRenderer;
import kr.or.ddit.common.paging.SearchCondition;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;


@Controller
@RequestMapping("/member/memberList.do")
public class MemberListController {
	@Inject
	private MemberService service;
	
	@RequestMapping
	public String list(
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
		
		return "member/memberList";
	}
}



















