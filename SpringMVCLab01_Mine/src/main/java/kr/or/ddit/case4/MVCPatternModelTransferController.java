package kr.or.ddit.case4;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


//뷰에 Model을 전달하는 전형적인 2가지 방법이고
//어떤 방법을 이용하든 컨트롤러 안에서는 모델과 뷰만 결정되면 됨!
@Controller
@RequestMapping("/case4")
public class MVCPatternModelTransferController {
	
	@GetMapping("receiveContent1")
	public String handler1_html(Model model) {
		String info = "이미 가공된 정보";
		model.addAttribute("info", info);
		return "case4/modelView1";
	}
	
	@GetMapping(value = "receiveContent1", produces = MediaType.APPLICATION_JSON_VALUE) //accept헤더를 응답Content-type으로 반영해 주기위한 속성으로 produces 이용
	public String handler1_json(Model model) {
		String info = "이미 가공된 정보";
		model.addAttribute("info", info);
		return "jsonView";
	}
	
	@GetMapping("receiveContent2")
	public ModelAndView handler2(Model model) {
		String info = "이미 가공된 정보";
		ModelAndView mav = new ModelAndView();
		mav.addObject("info", info);
		mav.setViewName("case4/modelView1");
		return mav;
	}
	
	
}
