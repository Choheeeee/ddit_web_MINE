package kr.or.ddit.case6;

import java.util.Map;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.case6.vo.CommandObject;
import lombok.extern.slf4j.Slf4j;

/**
 * url : /case6/parameterProcess
 * 모든 연산의 처리가 완료되면, 연산의 처리 결과는 (GET)핸들러를 통해 formView에서 출력함.
 * model명 : result
 * 
 * post 요청 핸들러 메소드는 2가지 방식으로 구현할 것
 * 낱개의 파라미터를 별개 수신하는 방식과, commandObject를 활용하는 구조
 *
 */
@Slf4j
@Controller
@RequestMapping("/case6/parameterProcess")
public class ParameterProcessController {
	
	@GetMapping
	public String formUI(Model model) {
		return "case6/formView";
	}
	
//	@PostMapping
	public String processEach(@RequestParam double left, @RequestParam double right, RedirectAttributes redirectAttributes) { //Model은 request스코프에 사용되어 redirection시 데이터가 사라져버림. 그래서 session 스코프인 RedirectAttributes 객체를 이용해야함
		double result = left + right;
		
		redirectAttributes.addFlashAttribute("result", result);
		return "redirect:/case6/parameterProcess";
	}
	
	@PostMapping
	public String processAll (@ModelAttribute("object") CommandObject object, RedirectAttributes redirectAttributes) {
		double result = object.getLeft() + object.getRight();
		object.setResult(result);
		
		redirectAttributes.addFlashAttribute("result", object); //파라미터를 쿼리스트링에 노출하고 싶지 않을때 FlashAttribute()이용
		return "redirect:/case6/parameterProcess";
	}
}
