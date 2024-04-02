package kr.or.ddit.mission.ajax;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.mission.vo.PropertyVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mission/ajax/propView")
public class PropertyInsertAjaxController {

	//WEB-INF폴더에 있으므로 우선 Get요청을 핸들링 할 컨트롤러가 필요함.
	//get핸들러에서 Model 전달 X => 비동기요청(=Json)을 사용하므로, 백그라운드 코드인 스프링 커스텀태그를 사용하지 않겠다는 의미(처음 get요청시엔 데이터없이 UI만 띄우면 되므로, 다시 비동기 요청이 발생할때 그때 데이터를 보내는 구조)
	@GetMapping
	public void formUI() {
		
	}
	
	@PostMapping //FormData니까 폼의 파라미터 핸들링하는 핸들러 (JSON이외의 다른 컨텐츠타입의 요청은 이 핸들러에서 처리하고, 응답데이터는 두 핸들러 모두 jsonView를 통해 JSON을 내보냄)
	public String processFormData(@Valid @ModelAttribute("property") PropertyVO property, Errors errors, RedirectAttributes redirectAttributes) {
		if(! errors.hasErrors()) {
			log.info("command object : {}", property);
			
//			message 속성 전달 (프로퍼티 등록 성공)
			String message = "프로퍼티 등록 성공~!";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/"; //웰컴페이지를 의미
		}else {
			return "jsonView"; //검증에 통과하지 못했을땐, 입력한 값을 담고있는 comandObject인 property와, errors를 갖고 다시 view로 간다.
		}
	}
	
	//서버사이드에선 클라이언트로부터 온 요청을 consume하므로, 클라이언트로부터 온 요청의 컨텐트타입을 식별하게 해주는 키워드임. 따라서, JSON타입의 요청이 오면 이 핸들러가 맡겠다는 의미.
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public String processJsonPayload(@Valid @RequestBody PropertyVO property, Errors errors, RedirectAttributes redirectAttributes) { //@RequestBody는 요청바디에 들어있는 json객체를 commandObject에 맵핑
		if(! errors.hasErrors()) {
			log.info("command object : {}", property);
			
//			message 속성 전달 (프로퍼티 등록 성공)
			String message = "프로퍼티 등록 성공~!";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/"; //웰컴페이지를 의미
		}else {
			return "jsonView"; //검증에 통과하지 못했을땐, 입력한 값을 담고있는 comandObject인 property와, errors를 갖고 다시 view로 간다.
		}
	}
}