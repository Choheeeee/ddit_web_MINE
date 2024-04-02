package kr.or.ddit.mission;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * url : /mission/receiveHeader.nhn 요청 처리
 * method : POST요청으로 제한
 * --> 핸들러 메소드 내에서
 * 	해당 요청에 포함된 body의 content-type을 로그로 출력할것
 * 단, 해당 헤더가 없다면 400응답을 전송
 */
@Controller
@Slf4j
@RequestMapping("/mission")
public class HeaderMissionController {

	
	@PostMapping("/receiveHeader.nhn")
	public void handler(@RequestHeader(value = "content-type", required=true)  String contentType) {
		log.info("content-Type : {}", contentType);
	}
}
