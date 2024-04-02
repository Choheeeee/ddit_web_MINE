package kr.or.ddit.prod.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.common.paging.BootstrapFormBasePaginationRenderer;
import kr.or.ddit.common.paging.DefaultFormBasePaginationRenderer;
import kr.or.ddit.common.paging.DefaultPaginationRenderer;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.PaginationRenderer;
import kr.or.ddit.common.paging.SearchCondition;
import kr.or.ddit.others.advice.OthersControllerAdvice;
import kr.or.ddit.others.dao.OthersDAO;
import kr.or.ddit.others.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodList.do")
public class ProdListControllerServlet extends HttpServlet{
	private ProdService service = new ProdServiceImpl();
	private OthersControllerAdvice advice = new OthersControllerAdvice();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		advice.addLprodList(req);
		advice.addBuyerList(req);
		
		Enumeration<String> paramterNames = req.getParameterNames();
		Map<String, Object> detailCondition = new LinkedHashMap<>();
		while (paramterNames.hasMoreElements()) {
			String name = (String) paramterNames.nextElement();
			detailCondition.put(name, req.getParameter(name));
		}
		
		req.setAttribute("condition", detailCondition);
		
		String pageStr = req.getParameter("page");
		int currentPage = 1; 
		if(StringUtils.isNumeric(pageStr)) {
			currentPage = Integer.parseInt(pageStr);
		}
		PaginationInfo paging = new PaginationInfo();
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition);
		
		List<ProdVO> prodList = service.retrieveProdList(paging);
		
		
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#searchForm");
		
		String pagingHTML = renderer.renderPagination(paging);
		
		req.setAttribute("prodList", prodList);
		req.setAttribute("pagingHTML", pagingHTML);
		
		String logicalViewName = "prod/prodList";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
}










