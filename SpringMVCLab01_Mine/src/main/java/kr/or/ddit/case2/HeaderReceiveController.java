package kr.or.ddit.case2;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case2")
public class HeaderReceiveController {
	
	@GetMapping("header1")
	public void handler(HttpServletRequest request) {
		String accept = request.getHeader("accept");
		log.info("accept 헤더 : {}", accept);
	}
	
	
	@GetMapping("header2")
	public void handler2(@RequestHeader("accept") String accept) {
		log.info("accept 헤더 : {}", accept);
	}
	
	@GetMapping("header3")
	public void handler3 (@RequestHeader(value="myheader", required = false, defaultValue = "0") int customHeader) {
		log.info("custom 헤더 : {}", customHeader);

	}
	
	@GetMapping("header4") //어떤 헤더 요소가 있는지 명확히 모를때 Map타입으로 전체 헤더를 받아온다.
	public void handler4(@RequestHeader Map<String, String> headers) {
		log.info("headers : {}", headers);
		log.info("accept : {}", headers.get("accept"));
		
	}
	
	@GetMapping("header5") //스프링은 한 키에 여러 값이 있을때, List<Map<String, String>처럼 멀티벨류맵 지원
	public void handler5(@RequestHeader MultiValueMap<String, String> headers) {
		log.info("headers : {}", headers);
		log.info("accept : {}", headers.get("accept"));
		
	}
	
	
	@GetMapping("header6") 
	public void handler6(@RequestHeader HttpHeaders headers) {
		log.info("headers : {}", headers);
		log.info("accept : {}", headers.getAccept());
	}
	
	
}
