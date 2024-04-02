package kr.or.ddit.dummy.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.vo.CartVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller	//HandlerMapping에의해 수집되고, 빈으로 등록되기위한 이 2개의 조건을 모두 만족하는 어노테이션은 Controller뿐임
public class DummyController {

	@RequestMapping("/index.do")
	public String index(Model model) {
		model.addAttribute("info", "동적 모델");
		return "admin:index";	//이 ViewName을 InternalResourceResolver가 수집함
//		return "jsonView";	//Accept헤더에 어떤 마임타입이 오든 json형태로 응답하겠다는 의미
							//이 ViewName을 BeanNameViewResolver가 수집해서 JsonView가 작동함
	}
	
	
	@ModelAttribute("cart")	//모든핸들러가 동작하기 전에 가장먼저 실행되는데, 이름이 cart인 모델이 되고, request에 넣어줌. 그래서 Get핸들러의 아규먼트에서 달라고 안해도 됨.
	public CartVO cart() {
		return new CartVO();
	}
	
	@GetMapping("/insertCart.do")
	public String dummyFormUI() {
		return "user:insertCart";
	}
	
	
	@PostMapping("/insertCart.do")
	public String dummyProcess(@Valid @ModelAttribute("cart") CartVO cart, BindingResult errors) { //BindingResult검증 결과를 담기위한 객체
		log.info("error : {}", errors.getErrorCount());
		if(errors.hasErrors()) {
			return "insertCart";
		}else{
			log.info("서버에 저장 완료");
			return "redirect:/index.do";
		}
	}
}
