package kr.or.ddit.common.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kr.or.ddit.enumpkg.MediaType;

/**
 * JSON response content 를 전송하는 view layer
 *
 */
@WebServlet("/jsonView.do")
public class JsonViewServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		
		
//		ServerTimeServlet에서 받은 model이 자바8 이후에 도입된 java.time패키지의 ZoneDateTime을 쓰고 있으므로,
//		Java 8이후에 도입된 날짜 및 시간 관련 클래스를 JSON으로 직렬화 및 역직렬화하기 위해 jackson-datatype-jsr310.jar 라이브러리가 필요하고
//		그 라이브러리가 지원하는 JavaTimeModule API이용
		
		//ObjectMapper 인스턴스를 생성한 후에 registerModule(), disable() 메서드를 통해 각종 모듈 등을 미리 등록한다.
		//이렇게 미리 등록해두고 나중에 ObjectMapper가 JSON 데이터를 처리할 때 이 설정값들을 적용하는 방식 
		ObjectMapper objectMapper = new ObjectMapper()
									.registerModule(new JavaTimeModule())
									.disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS) //나노초 정밀도 비활성화
									
									//타임스탬프는 1970년 1월 1일 00:00:00 부터 현재까지의 경과된 시간을 초로 나타낸 정수값
									//이걸 비활성화 함으로써, 사람이 읽고쓰기 쉬게 yyyy mm dd 식으로 표현
									.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		//ServerTimeServlet에서 보내준 Model을 rep객체를 통해 얻은후, 얻은 값을 저장할 Map
		Map<String, Object> nativeData = new HashMap<>();
		
		//ServerTimeServlet에서 보내준 Model의 이름이 "now"인데, 그 이름을 얻고 attrNames에 저장
		Enumeration<String> attrNames = req.getAttributeNames();
		
//		JSON으로 변환할 데이터 수집하기 위해, 위에서 얻은 이름들을 순회
		while (attrNames.hasMoreElements()) {
			String modelName = (String) attrNames.nextElement(); //String으로 형변환해서 modelName에 저장
			Object model = req.getAttribute(modelName); //modelName으로 값을 얻고 model에 저장
			nativeData.put(modelName, model); //modelName + model을 key:value로 Map에 저장
		}
		
		try(
			PrintWriter out = resp.getWriter();	//출력스트림만 열것 뿐, 클라이언트에게 전송한건 아직 아님!!
		){
			objectMapper.writeValue(out, nativeData); //마샬링(자바객체 -> json) + 직렬화(객체->이진데이터) 한후, 클라이언트에게 전송이 실제로 발생하는 코드
		}
	}
}


















