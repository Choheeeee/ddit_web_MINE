package kr.or.ddit.mission;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * url : /mission/now.nhn
 * method : GET
 * model : 현재 시각, model명 : now
 * response content type : HTML / JSON
 *
 */
@Slf4j
@Controller
@RequestMapping("/mission/now.nhn")
public class ModelAndViewMissionController {
	@Inject
	private NowGeneratorService service;

	@GetMapping //json 이외의 조건에 해당하는 요청을 맡는 핸들러
	public String handlerHTML (Model model) { //메서드 시그니처중 반환타입인 String은 logicalViewName의 타입을 의미함
		LocalDateTime now = service.receiveNow();
		model.addAttribute("now", now);
		return "mission/resultView";
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public void handlerJSON(Model model) { //내부적으로 content negotiator가 작동해서, client의 accept헤더를 동적으로 파악해 해당 뷰로 자동으로 이동시킴.
		LocalDateTime now = service.receiveNow();
		model.addAttribute("now", now);
//		return "jsonView";
		
	}
	
}
