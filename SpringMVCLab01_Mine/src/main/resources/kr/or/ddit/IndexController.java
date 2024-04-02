package kr.or.ddit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/index.do") //@webServlet과 같은 의미지만 Spring은 이 어노테이션 사용
	public String indexHandler() {
		return "index";
	}
}
