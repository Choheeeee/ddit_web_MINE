package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.others.advice.OthersControllerAdvice;
import kr.or.ddit.others.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController {
	
	@Inject
	private ProdService service;
	
	
	//메서드앞에 이게 붙어있으면, 모든 핸들러메서드를 호출하기전에 이걸 먼저 호출함. 다른 핸들러보다 가장 먼저 호출됨.
	@ModelAttribute("prod")
	public ProdVO prod() {
		return new ProdVO();
	}
	
	// view layer 로 연결하기 위한 컨트롤러
	@GetMapping
	public String insertForm() { //Get핸들러는 뷰를 로드할때 꼭 Model을 가져가야하는데, 아래 insertProcess에서도 중복되므로, 위의 메서드에 따로 만들어둔다. 그래서 아규먼트에 @ModelAttribute("prod") ProdVO prod가 생략됨.
								//위의 메서드에서 반환된 ProdVO객체가 2개의 핸들러메서드에서 재사용됨.
		return "prod/prodForm";
	}
	
	// form 을 통해 전송된 데이터에 대한 처리를 위한 컨트롤러 
	@PostMapping
	protected String insertProcess(
			@Validated(InsertGroup.class) @ModelAttribute("prod") ProdVO prod
			, BindingResult errors
			, Model model
	){
	
		String logicalViewName = null;
		if(! errors.hasErrors()) {
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
				
				model.addAttribute("message", message);
		}else {
			logicalViewName = "prod/prodForm";
		}
		
		return logicalViewName;
	}
}	
