package kr.or.ddit.case9;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.case9.vo.PayloadVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/case9")
@Slf4j
public class JsonRequestController {

	@PostMapping("jsonPayload1") //속성으로 복수개를 갖고있는 json객체 1개를 요청으로 수신하려고 하는데, 이건 body가 있음을 의미함. body는 post요청이므로 @PostMapping 이고,
								//우리가 필요한 json객체는 request body에 들어있으므로 @RequestParameter가 아닌 @RequestBody로 명시해준다.
	@ResponseBody //응답할때 이용하는 어노테이션
	public  Map<String, Object> handler1(@RequestBody Map<String, Object> payload) {
		log.info("내가 필요한 payload : {}");
		return payload;
	}
	
	@PostMapping("jsonPayload2")
	@ResponseBody
	public  PayloadVO handler2(@RequestBody PayloadVO payload) {
		log.info("내가 필요한 payload : {}");
		return payload;
	}
}
