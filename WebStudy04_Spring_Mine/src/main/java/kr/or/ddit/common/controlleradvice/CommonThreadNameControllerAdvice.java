package kr.or.ddit.common.controlleradvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(basePackages = "kr.or.ddit") //Advice의 타겟을 Controller라고 제한한것 / point cut = basePackages //Advice +  PointCut = Aspect
public class CommonThreadNameControllerAdvice {
	
	@ModelAttribute("threadName")	//모든 컨트롤러는 모델로 threadName을 만들어냄.
	public String threadName() {
		return Thread.currentThread().getName();
	}
}
