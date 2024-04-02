package kr.or.ddit.case3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/case3")
@Slf4j
public class Model2CaseController {

	@GetMapping("sendRequest1")
	public String handler1() {
		String logicalViewName = "case3/inner1"; //우리가 기존에 쓰던 컨트롤러의 5번단계가 생략되어 스프링내부에서 실행해주므로, 4번까지만 코딩하면됨
		return logicalViewName; //뷰에대한 정보 => 뷰의 이름을 반환함
	}
	//What's going on inside Spring
	//Forwarding (개발자가 직접 포워딩하기 위해서, RequestDispatcher를 사용할 일이 없음. RequestDispatcher의 상위객체인 request도 사용하지 않으므로)

	
	@GetMapping(value = "sendRequest2")
	public String handler2() {
		return "jsonView";
		
	}
	
	@RequestMapping(value = "sendRequest3", method = RequestMethod.GET, produces = "text/html" ) //accept헤더가 html이면 spring이 알아서 jsp등을 찾음
	public void handler3() {
		log.info("~!~!~!~!~!HTML요청!~!~!~!~!~!~!~!");
		//뷰네임을 return해 주지않아서, mapping url에서 강제로 view name을 만들어버림
		//404의 원인 : 컨트롤러를 못찾았을때, 뷰를 못찾았을때
	}
	
	@GetMapping(value = "sendRequest3", produces = "application/json") //accept헤더가 json이면 spring이 알아서 json을 만드느라 마샬링을 함
	public void handler3_json() {
		log.info("%%%%%%%%%%%%%%%%JSON요청%%%%%%%%%%%%%%%%%%");
	}
	
	
	
}
