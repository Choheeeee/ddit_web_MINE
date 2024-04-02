package kr.or.ddit.case8;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.case8.vo.InfoVO;

@Controller
@RequestMapping("/case8")
public class JsonResponseController {
	
	@GetMapping("jsonResp1")
	public String handler1(Model model) {
		String info = "동적으로 생성한 모델";
		model.addAttribute("info",info);
		return "jsonView";
	}
	
	@GetMapping("jsonResp2") //1개의 컨트롤러로 2가지 content-type을 핸들링 하고있음.
	public void handler2(Model model) {
		String info = "동적으로 생성한 모델"; 	//logicalViewName이 없이 void여도 content-negotiator가 작동해서, case8/jsonResp2.jsp로 보냄.
		model.addAttribute("info",info);
//		return "case8/jsonResp2";
	}
	
	@GetMapping(value = "jsonResp3", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String handler3() { 
		String info = "동적으로 생성한 모델";
		return info;
	} //모델을 1개밖에 못내보내는 구조인 handler3
	
	
	//여러개의 모델을 응답으로 내보내고 싶다면? => VO나 Map을 이용
	@GetMapping(value = "jsonResp4", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> handler4() {
		String info1 = "동적으로 생성한 모델";
		String info2 = "동적으로 생성한 모델";
		Map<String, Object> jsonObj = new HashMap<>();
		jsonObj.put("info1", info1);
		jsonObj.put("info2", info2);
		return jsonObj;
	}
		
		
	@GetMapping(value = "jsonResp5", produces = "application/json;charset=utf-8")
	public String handler5(Model model) {
		String info1 = "동적으로 생성한 모델";
		String info2 = "동적으로 생성한 모델";
		model.addAttribute("info1", info1);
		model.addAttribute("info2", info2);
		return "jsonView";
	}
	
	@GetMapping(value = "jsonResp6")
	public String handler6(Model model) {
		String info1 = "동적으로 생성한 모델1"; //jsonIgnore 와 transient 때문에 info1은 보호되어 응답으로 나가지않음.
		String info2 = "동적으로 생성한 모델2";
		InfoVO vo = new InfoVO();
		vo.setInfo1(info1);
		vo.setInfo2(info2);
		
		model.addAttribute("info", vo);
		return "jsonView";
	}
	
	@GetMapping(value = "jsonResp7") //6번과 7번의 차이는, 6번은 jsonView를 쓰고 있으므로, model로 객체가 한번 감싸져 있음
									//7번은 객체만 바로 내보내기 위해 @ResponseBody를 이용하고 Model대신 vo를 바로 리턴한다.
	@ResponseBody 
	public InfoVO handler7() {
		String info1 = "동적으로 생성한 모델1"; //jsonIgnore 와 transient 때문에 info1은 보호되어 응답으로 나가지않음.
		String info2 = "동적으로 생성한 모델2";
		InfoVO vo = new InfoVO();
		vo.setInfo1(info1);
		vo.setInfo2(info2);
		
		return vo;
	}
}
