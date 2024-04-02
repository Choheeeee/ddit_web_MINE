package kr.or.ddit.resource;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link Resource} : 모든 종류의 자원에 대한 접근 방법을 추상화 시켜놓은 타입.
 * {@link ResourceLoader} : 자원을 검색하고 메모리에 로딩하는 객체
 * 1. 파일 시스템 자원 - D:\01.medias\images\손석구3.jpg
 * 2. 클래스패스 자원 - kr/or/ddit/db/dbInfo.properties
 * 3. 웹 자원 - https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png
 *
 */
@Slf4j
public class ResourceLoaderDesc {
	public static void main(String[] args) throws MalformedURLException {
//		File fileSystemFile = new File("D:/01.medias/images/손석구3.jpg");
//		String realPath = ResourceLoaderDesc.class.getResource("/kr/or/ddit/db/dbInfo.properties").getFile(); //클래스로더를 이용해서 논리주소를 물리주소로 바꿔주는 과정
//		File classpathFile = new File(realPath);
//		URL url = new URL("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
		
		ConfigurableApplicationContext context
								= new ClassPathXmlApplicationContext("kr/or/ddit/resource/conf/resource-context.xml");
		
		context.registerShutdownHook();
		
		//file, classpath 등 prefix만 갈아끼우면 모든 종류의 리소스에 통일된 방법으로 접근
		Resource fsRes = context.getResource("file:D:/01.medias/images/손석구3.jpg");
		log.info("file system resouce : {}", fsRes);
		Resource cpRes = context.getResource("classpath:kr/or/ddit/db/dbInfo.properties");
		log.info("file system resouce : {}", cpRes);
		Resource webRes = context.getResource("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
		log.info("file system resouce : {}", webRes);
		
		VariousVO vo = context.getBean(VariousVO.class);
		log.info("주입결과 : {}", vo);
		
		OtherVariousVO others = context.getBean(OtherVariousVO.class);
		log.info("주입결과 : {}", others);
		
	}
}
