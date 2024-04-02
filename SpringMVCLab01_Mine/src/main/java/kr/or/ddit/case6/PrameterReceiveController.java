package kr.or.ddit.case6;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.case6.vo.Case6DummyVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case6")
public class PrameterReceiveController {

	@GetMapping("sendParameter1") //핸들러의 아규먼트를 통해 필요한 param1을 받으면 됨
	public void handler1(@RequestParam(required = true) String param1) { //required=false면, 해당 파라미터가 옵셔널함으르 의미 / value를 생략하면, 메소드의 아규먼트와 똑같은 이름으로 된 parameter를 찾음.
		log.info("param1 : {}", param1);
		
	}
	
	@GetMapping("sendParameterAndHeader")
	public void handler2(String param1, @RequestHeader("accept-language") String acceptLanguage) {	//String acceptLanguage에 헤더의 실제 속성이름인 accept-Language 하이픈을 넣을 수 없으므로, 네임매칭이 필요함. 
																									//그래서 @RequestHeader(accept-language)를 명시해줘야함
		log.info("param1 : {}", param1);
		log.info("acceptLanguage : {}", acceptLanguage);
	}
	
	
	@GetMapping("sendParameter3") //@RequestParam 어노테이션 이름이 단수형인데, 파라미터를 낱개로 받을때 사용함
	public void handler3(@RequestParam(required = false, defaultValue = "1") int param1) { 
		log.info("param1 : {}", param1);
		
	}
	
	@GetMapping("sendParameter4")
	public void handler4(@RequestParam Map<String, Object> parameterMap) { //한개의 키로 한개의 값밖에 못받아서, 같은이름으로 다른 값이 오면 버려지는 상황이 발생 => 핸들러5번에서 MultiValueMap으로 해결
		log.info("parameterMap : {}", parameterMap);
	}
	
	@GetMapping("sendParameter5")
	public void handler5(@RequestParam MultiValueMap<String, Object> parameterMap) {	// {param1=[value1, value2]} 이렇게 한개의 키에 여러 값들이 존재할 수 있게 됨.(Map은 VO대신 동적으로 구현할때 사용됨 -> 여기서 commandObject와 같은 역할을 하고있음)
		log.info("parameterMap : {}", parameterMap);
	}
	
	@GetMapping("sendParameter6") //@ModelAttribute 어노테이션은 파라미터가 commandObject의 단위일때 여러개의 파라미터를 받을때 이용함.
	public String handler6(@ModelAttribute("dummy") Case6DummyVO dummy) {	//받은 파라미터들을 vo에 넣는 PopulateUtils의 역할, 즉 commandObject 역할을 Spring이 내부에서 이미 그대로 하고 있음
		log.info("dummy : {}", dummy);
		return "case6/modelView1";
	}
}
