package kr.or.ddit.buyer.servlet;

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

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.common.paging.BootstrapFormBasePaginationRenderer;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.PaginationRenderer;
import kr.or.ddit.others.dao.OthersDAO;
import kr.or.ddit.others.dao.OthersDAOImpl;
import kr.or.ddit.vo.BuyerVO;

/**
 * screenSize : 3, blockSize : 2 를 기준으로 페이징 처리.
 * 다음과 같은 검색 상황에 대한 지원.
 * P101 이라는 분류에 해당하는 제조사중 서울 지역의 제조사 중 삼성전자라는 이름이 포함된 제조사 정보를 검색함.
 *
 */
@WebServlet("/buyer/buyerList.do")
public class BuyerListControllerServlet extends HttpServlet{
	private BuyerService service = new BuyerServiceImpl();
	
	private OthersDAO dao = new OthersDAOImpl();
	public void addLprodList(HttpServletRequest req) {
		req.setAttribute("lprodList", dao.selectLprodList());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addLprodList(req);
		
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
		PaginationInfo paging = new PaginationInfo(3, 2);
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition);
		
		List<BuyerVO> buyerList = service.retrieveBuyerList(paging);
		
		
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#searchForm");
		
		String pagingHTML = renderer.renderPagination(paging);
		
		req.setAttribute("buyerList", buyerList);
		req.setAttribute("pagingHTML", pagingHTML);
		
		String logicalViewName = "buyer/buyerList";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
}
