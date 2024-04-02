package kr.or.ddit.others.advice;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.others.dao.OthersDAO;

@ControllerAdvice(basePackages  = "kr.or.ddit.prod")
public class OthersControllerAdvice {
	@Inject
	private OthersDAO dao;
	
	@ModelAttribute("lprodList") //메서드에 이 어노테이션을 쓰고, 모델을 바로 반환하면 해당모델이 스코프로 공유됨.
	public List<Map<String, Object>> addLprodList() {
		return dao.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<Map<String, Object>> addBuyerList() {
		return dao.selectBuyerList();
	}
}
