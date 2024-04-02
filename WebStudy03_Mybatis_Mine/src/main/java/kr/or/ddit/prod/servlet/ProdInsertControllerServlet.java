package kr.or.ddit.prod.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.others.advice.OthersControllerAdvice;
import kr.or.ddit.others.dao.OthersDAO;
import kr.or.ddit.others.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
public class ProdInsertControllerServlet extends HttpServlet{
	private ProdService service = new ProdServiceImpl();
	
	private OthersControllerAdvice advice = new OthersControllerAdvice();
	
	// view layer 로 연결하기 위한 컨트롤러
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		advice.addLprodList(req);
		advice.addBuyerList(req);
		
		String logicalViewName = "prod/prodForm";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
	
	// form 을 통해 전송된 데이터에 대한 처리를 위한 컨트롤러
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		advice.addLprodList(req);
		advice.addBuyerList(req);
		
//		2. 18개의 파라미터를 받고 -> Command Object 로 캡슐화(ProdVO)
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		
		Map<String, String[]> parameterMap = req.getParameterMap();
		
		PopulateUtils.populate(prod, parameterMap);
		
		// 2-1. 검증 작업 : 통과, 불통
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		ValidateUtils.validate(prod, errors, InsertGroup.class);
		
		String logicalViewName = null;
		if(errors.isEmpty()) {
	//		3. 등록 로직 호출
				ServiceResult result = service.createProd(prod);
	//		4. 로직의 실행 결과에 따른 뷰 선택
				String message = null;
				switch (result) {
				case OK:
					logicalViewName = "redirect:/prod/prodView.do?what="+prod.getProdId(); // Post-Redirect-Get
					break;
				default:
					logicalViewName = "prod/prodForm";
					break;
				}
				
				req.setAttribute("message", message);
		}else {
			logicalViewName = "prod/prodForm";
		}
		
		
//		5. 해당 뷰로 이동.
		if(logicalViewName.startsWith("redirect:")) {
			String redirectViewPath = req.getContextPath() + logicalViewName.substring("redirect:".length());
			resp.sendRedirect(redirectViewPath);
		}else {
			req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
		}
		
	}
}	
