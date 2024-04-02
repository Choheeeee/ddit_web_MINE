package kr.or.ddit.case5;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case5")
public class FlowcontrolCaseController {

	//Forwad
	@GetMapping("start1")
	public String handler1_start(Model model) {
		LocalDateTime now = LocalDateTime.now();
		model.addAttribute("now", now);
		return "forward:/case5/dest1";
	}
	
	@GetMapping("dest1")
	public String handler1_dest(@RequestAttribute ("now") LocalDateTime now) { //required = true가 디폴트라서 생략돼있는데, 이 true의 의미는 now가 없이는 이 메서드를 탈 수 없음.
		log.info("forward로 전달된 model data : {}", now);
		return "case5/finalView1";
		
	}
	
	
	
	
	
	//Redirect
	@GetMapping("start2")
	public String handler2_start(RedirectAttributes redirectAttributes) { //파라미터에 RedirectAttributes을 요청하면 B에선 Model로 받아야한다.
		LocalDateTime now = LocalDateTime.now();
		redirectAttributes.addFlashAttribute("now", now);
		return "redirect:/case5/dest2";
	}
	
	@GetMapping("dest2")
	public String handler2_dest(Model model) { //required = true가 디폴트라서 생략돼있는데, 이 true의 의미는 Model인 now가 없이는 이 메서드를 탈 수 없음.
		log.info("redirect로 전달된 model data : {}", model.getAttribute("now"));
		return "case5/finalView2";
		
	}
}
