package kr.or.ddit.case2;

import java.time.LocalDateTime;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case2.conf.Case2JavaConfiguration;
import kr.or.ddit.case2.material.Bibitan;
import kr.or.ddit.case2.material.Gun;
import kr.or.ddit.case2.material.Hunter;
import kr.or.ddit.case2.material.ShotGun;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HuntingGround {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = 
//				new GenericXmlApplicationContext("classpath:kr/or/ddit/case2/conf/case2_context2.xml");
				new AnnotationConfigApplicationContext(Case2JavaConfiguration.class);
		context.registerShutdownHook();
		
		Hunter hunter = context.getBean(Hunter.class);
		LocalDateTime now = context.getBean(LocalDateTime.class);
		log.info("공격 시점 : {}", now);
		hunter.hunting();
		Thread.sleep(1000);
		Gun gun = context.getBean(Bibitan.class);
		hunter.setGun(gun);
		now = context.getBean(LocalDateTime.class);
		log.info("공격 시점 : {}", now);
		hunter.hunting();
		Thread.sleep(1000);
		gun = context.getBean(ShotGun.class);
		hunter.setGun(gun);
		now = context.getBean(LocalDateTime.class);
		log.info("공격 시점 : {}", now);
		hunter.hunting();
		
		
		
	}
	
}
