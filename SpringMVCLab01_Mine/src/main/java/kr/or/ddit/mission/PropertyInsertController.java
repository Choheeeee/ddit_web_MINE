package kr.or.ddit.mission;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.mission.vo.PropertyVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mission/propView")
public class PropertyInsertController {

	@GetMapping
	public void formUI(@ModelAttribute("property") PropertyVO property) {
//		return "mission/propView";
	}
	
	@PostMapping
	public String processFormData(@Valid @ModelAttribute("property") PropertyVO property, Errors errors, RedirectAttributes redirectAttributes) {
		if(! errors.hasErrors()) {
			log.info("command object : {}", property);
			
//			message 속성 전달 (프로퍼티 등록 성공)
			String message = "프로퍼티 등록 성공~!";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/"; //웰컴페이지를 의미
		}else {
			return "mission/propView"; //검증에 통과하지 못했을땐, 입력한 값을 담고있는 comandObject인 property와, errors를 갖고 다시 view로 간다.
		}
	}
}
