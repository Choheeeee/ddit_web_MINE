package kr.or.ddit.buyer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.vo.BuyerVO;

@WebServlet("/buyer/buyerView.do")
public class BuyerViewControllerServlet extends HttpServlet{
	private BuyerService service = new BuyerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String buyerId = req.getParameter("what");
		if(StringUtils.isBlank(buyerId)) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		req.setAttribute("buyer", buyer);
		
		String logicalViewName = "buyer/buyerView";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
}
