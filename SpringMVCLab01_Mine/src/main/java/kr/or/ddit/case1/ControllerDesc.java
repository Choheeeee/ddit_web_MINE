package kr.or.ddit.case1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case1/request1.do")
public class ControllerDesc {
	
	
	//class와 method로 이미 다른 뎁스로 분리돼있음. 따라서 메서드의 url엔 슬래쉬 생략
//	@RequestMapping( method = RequestMethod.GET)
	@GetMapping
	public void handler1() {
		log.info("handler method 1번 동작 (GET)");
	}
	
//	@RequestMapping( method = RequestMethod.POST)
	@PostMapping
	public void handler2() {
		log.info("handler method 2번 동작 (POST)");
	}
	
	
	@RequestMapping
	public void handler3() {
		log.info("handler method 2번 동작 (Others !!!)");
	}
	
	@RequestMapping("depth")
	public void asdf() {
		
	}
	
}
