package kr.or.ddit.buyer.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.others.advice.OthersControllerAdvice;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;

@WebServlet("/buyer/buyerUpdate.do")
public class BuyerUpdateControllerServlet extends HttpServlet{
	
	BuyerService service = new BuyerServiceImpl();
	OthersControllerAdvice others = new OthersControllerAdvice();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		others.addBuyerList(req);
		others.addLprodList(req);
		
		String buyerId = req.getParameter("what");
		if(StringUtils.isBlank(buyerId)) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		req.setAttribute("buyer", buyer);
		
		String logicalViewName = "buyer/buyerEdit";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//2. 파라미터를 받고,Command Object로 캡슐화 (BuyerVO)
		BuyerVO buyer = new BuyerVO();
		//CallByReference 방식인 req스코프를 이용해서 BuyerVO를 View로 보내기
		req.setAttribute("buyer", buyer);
		Map<String, String[]> parameterMap = req.getParameterMap();
		PopulateUtils.populate(buyer, parameterMap); //PopulateUtils이용해서 Command Object로 캡슐화
		
		Map<String, String> errors = new LinkedHashMap<String, String>();
		req.setAttribute("errors", errors);
		
		ValidateUtils.validate(buyer, errors, UpdateGroup.class);
		
		String logicalViewName = null;
		if(errors.isEmpty()) {
			ServiceResult result = service.modifyBuyer(buyer);
			String message = null;
			switch (result) {
			case OK:
				logicalViewName = "redirect:/buyer/buyerView.do?what=" + buyer.getBuyerId();
				break;

			default:
				logicalViewName = "buyer/buyerEdit";
				message = "서버 오류.. ㅈㅅ";
				break;
			}
		req.setAttribute("message", message);
		}else {
			logicalViewName = "buyer/buyerEdit";
		}
		
		if(logicalViewName.startsWith("redirect:")) {
			String redirectViewPath = req.getContextPath() + logicalViewName.substring("redirect:".length());
			resp.sendRedirect(redirectViewPath);
		}else {
			req.getRequestDispatcher("/"+ logicalViewName + ".miles").forward(req, resp);
		}
	}
	
}
