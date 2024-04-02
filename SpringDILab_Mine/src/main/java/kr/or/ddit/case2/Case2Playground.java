package kr.or.ddit.case2;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case2.material.Gun;
import kr.or.ddit.case2.material.Hunter;
import kr.or.ddit.case2.material.ShotGun;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Case2Playground {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new GenericXmlApplicationContext("classpath:kr/or/ddit/case2/conf/case2-context3.xml");
		context.registerShutdownHook();
		
//		Hunter hunter1 = context.getBean(Hunter.class);
//		hunter1.hunting();
//		Gun shotGun = context.getBean(ShotGun.class);
//		hunter1.setGun(shotGun);
//		hunter1.hunting();
//		
//		Hunter hunter2 = context.getBean(Hunter.class);
//		hunter2.hunting();
//		
//		log.info("hunter1 == hunter2 : {}", hunter1 == hunter2);
		
		
	}
}
