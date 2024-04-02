package kr.or.ddit.case1;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case1.conf.Case1JavaConfiguration;
import lombok.extern.slf4j.Slf4j;

/**
 * IoC Container, DI Container, Bean Container 사용단계
 * 1. pom.xml에 spring-context 의존성 추가
 * 2. spring bean configuratioin file (= bean metadata file) 생성
 * 		1) bean 등록 : bean -> class, id 등등
 * 		2) setter injection : property -> name(setter 종류 결정), ref(주입대상)
 * 		3) constructor injection(필수 전략인 경우 추천) : constructor-arg -> name(생성자 파라미터 결정), ref(주입 대상)
 * 3. 엔트리 포인트에서 컨테이너 객체를 생성하는 작업
 * 4. getBean의 형태로 컨테이너 내부의 빈을 주입받아 사용
 * 5. 어플리케이션 엔드포인트에서 컨테이너 종료 작업
 * 
 */
@Slf4j
public class ContainerDesc {

	public static void main(String[] args) {
		
		//컨테이너 생성 (쓸 수 있는 컨테이너의 종류)
		ConfigurableApplicationContext context = 
//				new ClassPathXmlApplicationContext("kr/or/ddit/case1/conf/case1-context.xml");
//				new FileSystemXmlApplicationContext("d:/case1-context.xml");
//				new GenericXmlApplicationContext("file:d:/case1-context.xml");
//				new GenericXmlApplicationContext("classpath:kr/or/ddit/case1/conf/case1-context.xml");
				new AnnotationConfigApplicationContext(Case1JavaConfiguration.class);
		context.registerShutdownHook();
		Foo foo = context.getBean(Foo.class);
		log.info("주입된 foo : {}", foo.toString());
	}
}
