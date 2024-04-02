package kr.or.ddit.others.advice;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.others.dao.OthersDAO;
import kr.or.ddit.others.dao.OthersDAOImpl;

public class OthersControllerAdvice {
	private OthersDAO dao = new OthersDAOImpl();
	public void addLprodList(HttpServletRequest req) {
		req.setAttribute("lprodList", dao.selectLprodList());
	}
	public void addBuyerList(HttpServletRequest req) {
		req.setAttribute("buyerList", dao.selectBuyerList());
	}
}
