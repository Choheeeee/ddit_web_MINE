package kr.or.ddit.autodi;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case1.Foo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutoDIPlayground {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/autodi/conf/autoDI-context.xml");
		
		context.registerShutdownHook();
		
		log.info("빈 갯수 : {}", context.getBeanDefinitionCount());
		Arrays.stream(context.getBeanDefinitionNames())
				.forEach(n -> log.info("id : {}",n));
		Foo foo = context.getBean(Foo.class);
		log.info("주입된 결과 : {}", foo);
		
	}
}
