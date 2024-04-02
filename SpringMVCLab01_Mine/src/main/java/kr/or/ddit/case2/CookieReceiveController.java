package kr.or.ddit.case2;

import java.net.URLDecoder;
import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller //bean으로 등록
@RequestMapping("/case2")
@Slf4j
public class CookieReceiveController {

	@GetMapping("cookie1") //핸들러 만들어주기
	public void handler1(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		Arrays.stream(cookies)
				.forEach(c->{
					try {
						
						String decoded = URLDecoder.decode(c.getValue(), "utf-8");
						log.info("{} : {}", c.getName(), decoded);
					}catch(Exception e) {
						throw new RuntimeException(e);
					}
				});
	}
	
	
	@GetMapping("cookie2")
	public void handler2(@CookieValue String sample2) {
		log.info("sample2 cookie : {}", sample2);
	}
}
