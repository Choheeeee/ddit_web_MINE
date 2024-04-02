package kr.or.ddit.properties;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Test;

import kr.or.ddit.servlet08.ServerTimeServlet;

class PropertiesFileReadTest {

	@Test
	void test() throws IOException, URISyntaxException {
		//설정파일을 비휘발성으로 Map타입으로 관리해주는 Properties객체 (Map => property name(=Message code, String타입만 가능) = property value)
		Properties properties = new Properties();
		String logicalPath = "/kr/or/ddit/message/message-common_en.properties";
		URL realPath = ServerTimeServlet.class.getResource(logicalPath);
		File file = new File(realPath.toURI());
		try(
			FileInputStream fis = new FileInputStream(file);
		){
			properties.load(fis);
			System.out.println(properties.size());
			properties.forEach((n,v)->{
				System.out.printf("%s : %s\n", n, v);
			});
			properties.setProperty("newProp","newValue");
		}
		
	}

	
	@Test
	void testResourceBundle() {
		//Properties 와 ResourceBundle 객체의 차이점 : ResourceBundle객체는 ReadOnly
		
		//설정파일을 관리해주는 객체인 ResourceBundle는 객체를 생성할때 new를쓰는 Properties객체와 달리, 펙토리 메서드를 이용함
		//getBundle의 인자인 baseName은 확장자가 필요하지 않음
		String baseName =  "kr/or/ddit/message/message-common";
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, Locale.KOREAN);	//팩토리 방식으로 ResourceBundle객체 생성하고
																	//getBundle()메서드 내부적으로 입력스트림이 작동함
		bundle.keySet().forEach(mc->{
			System.out.printf("%s : %s\n",mc,bundle.getString(mc));
		});
	}
}
