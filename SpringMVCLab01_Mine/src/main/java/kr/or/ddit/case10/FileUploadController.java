package kr.or.ddit.case10;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.case10.vo.UploadFileVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case10/upload2")
//클라이언트가 서버에 파일을 업로드하면 서버가 어떻게 처리하는지 - 스프링 프레임워크(MultipartFile & Resource 객체)를 사용하여 파일 업로드를 처리 => case2
public class FileUploadController {
	@Value("file:d:/saveFiles/") //@Value 이 어노테이션을 통해, 외부 설정 파일이나 어플리케이션 컨텍스트에서 가져온 값을 saveFolder 필드에 주입함
	private Resource saveFolder; //스프링 프레임워크의 Resource API를 이용 => 파일이 저장될 기본 디렉토리
									
	
	@PostConstruct //생성되고 모든 주입이 끝난 직후에 실행되는 라이프싸이클 콜백메서드
	public void init() {
		log.info("saveFolder : {}", saveFolder);
	}
	@GetMapping
	public String formUI(@ModelAttribute("fileVO") UploadFileVO fileVO) {
		return "case10/uploadForm2";
	}
//	public String postHandler1(HttpServletRequest req) throws IOException, ServletException {
//		log.info("uploader : {}", req.getParameter("uploader"));
//		log.info("count : {}", req.getParameter("count"));
//		log.info("uploadFile : {}", req.getPart("uploadFile"));
//		return "jsonView";
//	}
//	//Part를 여기서 직접 쓰면 서블릿이 3.x대의 버전에 종속돼서 이건 스프링이 지향하는 포조가 아님. 그래서 여기에선 그 종속성을 없애기 위해 어댑터(=wrapper) 패턴을 구현한 MultipartFile 객체를 쓴다.
//	public String postHandler2(
//			@RequestParam(required = false) String uploader
//			, @RequestParam(required = false, defaultValue = "1") int count
//			, @RequestPart(required = true) MultipartFile[] uploadFile) throws IOException { //MultipartFile을 배열로 설정해서 복수개 파일업로드 해보기
//		
//		log.info("uploader : {}", uploader);
//		log.info("count : {}", count);
//		log.info("uploadFile : {}", uploadFile);
//		for(MultipartFile single : uploadFile) {
//			if(single.isEmpty()) continue;
//			String saveName = saveToResource(single, saveFolder);
//			log.info("original file name : {}, save name : {}", single.getOriginalFilename(), saveName );
//		}
//		
//		return "jsonView";
//	}
	@PostMapping
	public String postHandler3(
			@Valid @ModelAttribute("fileVO") UploadFileVO commandObject
			, BindingResult errors
	) throws IOException { //MultipartFiled을 배열로 설정해서 복수개 파일업로드 해보기
		if(errors.hasErrors()) {
			return "case10/uploadForm2";
		}else {
			log.info("command object : {}", commandObject);
			log.info("uploadFile : {}", commandObject.getUploadFile());
			for(MultipartFile single : commandObject.getUploadFile()) { //getUploadFile() 클라이언트가 업로드한 실제 파일들
				if(single.isEmpty()) continue;
				String saveName = saveToResource(single, saveFolder);
				log.info("original file name : {}, save name : {}", single.getOriginalFilename(), saveName );
			}
			
			return "jsonView";
			
		}
	}
	
//	 클라이언트가 업로드한 파일을 서버의 파일 시스템에 저장하는 메서드 - 이 메서드는 MultipartFile 객체와 Resource 객체를 인자로 받아서 파일을 저장하고 저장된 파일의 이름을 반환
	private String saveToResource(MultipartFile single, Resource saveFolder) throws IOException {
		String saveName = UUID.randomUUID().toString(); // 고유한 파일 이름을 생성하기 위해 UUID를 사용
		Resource saveFileRes = saveFolder.createRelative(saveName); //saveFileRes는 기본루트 디렉토리인 saveFolder와 실제 파일의 이름인 saveName을 결합하여 파일에 접근할 수 있는 Resource를 나타냄
		File saveFile = saveFileRes.getFile(); //getFile() 메서드를 사용하여 Resource를 파일로 변환
		try(
			InputStream is = single.getInputStream() //MultipartFile에서 얻은 입력 스트림을 사용하여 파일을 서버의 파일 시스템에 복사
		){
			FileUtils.copyInputStreamToFile(is, saveFile);	//파일 카피뜨기
			return saveName;
		}
	}	//이렇게 함으로써 MultipartFile에서 얻은 파일 내용을 고유한 이름으로 저장하여 서버의 파일 시스템에 보관함. 
		//주의할 점은 saveFolder는 미리 존재해야 하며, 그 하위에 저장될 파일의 경로를 나타내는 saveName을 생성하여 파일을 저장하는 방식임
}
