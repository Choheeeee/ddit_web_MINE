package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.paging.BootstrapFormBasePaginationRenderer;
import kr.or.ddit.common.paging.DefaultFormBasePaginationRenderer;
import kr.or.ddit.common.paging.DefaultPaginationRenderer;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.PaginationRenderer;
import kr.or.ddit.common.paging.SearchCondition;
import kr.or.ddit.others.advice.OthersControllerAdvice;
import kr.or.ddit.others.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod")
public class ProdRetrieveController {
	@Inject
	private ProdService service;
	
	@GetMapping("prodList.do")
	protected String list(
			@RequestParam Map<String, Object> detailCondition
			, @RequestParam(name="page", required = false, defaultValue = "1") int currentPage
			, Model model) {
		
		
		PaginationInfo paging = new PaginationInfo();
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition);
		
		List<ProdVO> prodList = service.retrieveProdList(paging);
		
		
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#searchForm");
		
		String pagingHTML = renderer.renderPagination(paging);
		
		model.addAttribute("pagingHTML", pagingHTML);
		model.addAttribute("prodList", prodList);
		model.addAttribute("condition", detailCondition);
		
		return "prod/prodList";
	}
	
	
	@GetMapping("prodView.do")
	public String prodOne(@RequestParam("what") String prodId, Model model) {
		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);
		
		return "prod/prodView";
	}
}