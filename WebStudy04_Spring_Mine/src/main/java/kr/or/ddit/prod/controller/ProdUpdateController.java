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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.ProdVO;


@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateController {
	
	@Inject
	private ProdService service;
	
	
	@GetMapping
	protected String updateForm(@RequestParam String what, Model model) {
		ProdVO prod = service.retrieveProd(what);
		model.addAttribute("prod", prod);
		return "prod/prodEdit";
	}
	
	@PostMapping
	protected String updateProcess(@Validated(UpdateGroup.class)  @ModelAttribute("prod")  ProdVO prod
			, BindingResult errors
			, Model model
			, RedirectAttributes redirectAttributes
	) {
		
		String logicalViewName = null;
		if(! errors.hasErrors()) {
			
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
				
				redirectAttributes.addFlashAttribute("message", message);
		}else {
			logicalViewName = "prod/prodEdit";
		}
		
		return logicalViewName;
	}
}










