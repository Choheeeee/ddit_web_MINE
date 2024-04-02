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
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodUpdate.do")
public class ProdUpdateControllerServlet extends HttpServlet {
	
	private ProdService service = new ProdServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prodId = req.getParameter("what");
		if(StringUtils.isBlank(prodId)) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		String logicalViewName = "prod/prodEdit";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		2. 17개의 파라미터를 받고 -> Command Object 로 캡슐화(prodVO)
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		Map<String, String[]> parameterMap = req.getParameterMap();
		PopulateUtils.populate(prod, parameterMap);
		
		// 2-1. 검증 작업 : 통과, 불통
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		ValidateUtils.validate(prod, errors, UpdateGroup.class);
		
		String logicalViewName = null;
		if(errors.isEmpty()) {
	//		3. 수정 로직 호출
				ServiceResult result = service.modifyProd(prod);
	//		4. 로직의 실행 결과에 따른 뷰 선택
				String message = null;
				switch (result) {
				case OK:
					logicalViewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
					break;
					
				default:
					logicalViewName = "prod/prodEdit";
					message = "서버 오류";
					break;
				}
				
				req.setAttribute("message", message);
		}else {
			logicalViewName = "prod/prodEdit";
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










