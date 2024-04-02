package kr.or.ddit.jackson;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.vo.CalculatorVO;


/**
 * jacson-databind를 이용한 마샬링과 직렬화
 * 		Marshalling : native data를 json/xml과 같은 공통 메세지 포맷으로 변환하는 작업. (일어 -> 영어)
 * 		UnMarshalling : json/xml과 같은 공통 메세지 포맷을 native data로 변환하는 작업 (영어 -> 독일어)
 * 
 * 		Serialization : 매체에 데이터를 전송이나 저장해야할때 객체를 byte stream(=byte array) 형태로 데이터를 변환 (0,1의 이진데이터로 변환)
 * 		DeSerialization : 매체에 저장된 혹은 매체로부터 전송된 byte stream을 원래의 데이터 형태로 변환하는 작업(이진데이터를 원래의 데이터형태로 변환하는 작업)
 */
class ObjectMapperTest {

	@Test
	void testMarshalling() throws JsonProcessingException {
		Map<String, Object> nativeData = new HashMap<>(); //객체
		nativeData.put("prop1", 323);
		nativeData.put("prop2", false);
		nativeData.put("prop3", "SAMPLE");
		nativeData.put("prop4", Collections.singletonMap("innerProp", 234));
		nativeData.put("prop5", null);
		nativeData.put("prop6", new int[] {1,2,3});
		
//		native -> json
//		marshalling : write* 메서드, unMarshalling : read* 메서드
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(nativeData);	//key가 ""로 묶여있는 json객체가 반환됨
		System.out.println(json);	//직렬화작업
	}
	
	@Test
	void testUnMarshalling() throws JsonMappingException, JsonProcessingException {
		String json = "{\"prop2\":false,\"prop1\":323,\"prop6\":[1,2,3],\"prop5\":null,\"prop4\":{\"innerProp\":234},\"prop3\":\"SAMPLE\"}"; //역직렬화
		ObjectMapper objectMapper = new ObjectMapper();
		
		//2번째 인자로 변환될 객체의 타입을 써주는데, Map은 인터페이스여서 구체적인 구현클래스가 없으므로 HashMap으로 지정
		Map<String, Object> nativeData = objectMapper.readValue(json, HashMap.class);	
		System.out.println(nativeData);
	}
	
	/**
	 *  I/O 작업 단계
	 *  	1. 매체
	 *  	2. 1번의 매체에 단방향 데이터의 통로인 Stream을 연결하여 개방함 - 1차 Stream
	 *  		ex) fis = new FileInputStream(file), SocketOutputStream, ByteArrayInputStream
	 *  	3. 1차 스트림을 대상으로 2차 스트림을 연결하여 개방함 ex) Buffered, Filtered 계열 (optinal)
	 *  		ex) new BufferedInputSteam(fis), DataInputStream, ObjectInputStream(역직렬화) / ObjectOutputStream(직렬화)
	 *  	4. EOF를 만날때까지 반복적인 read/write
	 *  	5. close (try with resource 구문)
	 * @throws FileNotFoundException 
	 */		
	@Test
	void testSerialization() {
		Map<String, Serializable> nativeData = new HashMap<>();
		nativeData.put("prop1", "SAMPLE");
		nativeData.put("prop2", 2345);
		
		File file = new File("d:/sample.dat");
		try(
				FileOutputStream fos = new FileOutputStream(file);	//매체
				ObjectOutputStream oos = new ObjectOutputStream(fos);
		){
			oos.writeObject(nativeData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//직렬화된 객체를 원래 객체로 복원하는 작업
	@Test
	void testDeSerialization() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File("d:/sample.dat");
		try(
			FileInputStream fis = new FileInputStream(file);	//위에서 직렬화된 파일을 읽어오는 주 입력스트림 (= 1차 스트림)
			ObjectInputStream ois = new ObjectInputStream(fis); //읽어온파일을 역직렬화 스트림에 넣어서 역직렬화하기 => 이진데이터를 객체로 복원
		){
			Map<String, Serializable> nativeData = (Map) ois.readObject();
			System.out.println(nativeData);
		}
	}
	
	@Test
	void testMarshallingAndSerialization() throws IOException {
		Map<String, Serializable> nativeData = new HashMap<>();
		nativeData.put("prop1", "SAMPLE");
		nativeData.put("prop2", 2345);
		
		File file = new File("d:/sample.json"); //미디어
		ObjectMapper objectMapper = new ObjectMapper();
		
		try(
			FileWriter writer = new FileWriter(file);
		){
			
			objectMapper.writeValue(writer, nativeData);
		}
		
	}
	
	//마샬링과 직렬화의 차이점은 마샬링은 이기종에서 발생
	//역직렬화와 언마샬링의 차이점은 언마샬링은 이기종에서 발생
	@Test
	void testDeSerializationAndUnMashalling() throws FileNotFoundException, IOException {
		File file = new File("d:/sample.json"); //미디어
		ObjectMapper objectMapper = new ObjectMapper();	//직렬화나 역직렬화를 위한 API
		
		try(
			FileReader rd = new FileReader(file);
		){
			
			Map<String, Serializable> nativeData = objectMapper.readValue(rd, HashMap.class);
			System.out.println(nativeData);
		}
	}
	
	@Test
	void testSerialization2() {
		CalculatorVO nativeData = new CalculatorVO(0, 0, OperatorType.PLUS);
		File file = new File("d:/sample.dat");
		try(
				
				FileOutputStream fos = new FileOutputStream(file);	//매체
				ObjectOutputStream oos = new ObjectOutputStream(fos);
		){
			oos.writeObject(nativeData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
