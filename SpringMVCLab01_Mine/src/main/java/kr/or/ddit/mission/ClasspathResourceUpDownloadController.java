package kr.or.ddit.mission;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.io.input.CharSequenceInputStream;
import org.apache.commons.lang3.function.Failable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/mission/file")
public class ClasspathResourceUpDownloadController {

	@Value("classpath:kr/or/ddit/images/")
	private Resource imageFolder;
	
	@GetMapping
	public String formUI() {	//UI 제공 = 유일하게 동기요청을 받는 컨트롤러
		
		return "mission/fileForm";	//LogicalViewName을 Definition에 반영해줘야함
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)	//갖고있는 이미지 목록을 데이터로 제공 => 이 데이터는 JSON 형태여야함
	@ResponseBody
	public String[] getImageList() throws IOException {
		return imageFolder.getFile().list(); //반환되는값은 String인데, 이걸 바로 응답으로 내보내기 위해 @ResponseBody
		
	}
	
	//file 1개 서비스함
	@GetMapping("{name}")
	public ResponseEntity<Resource> getFile(@PathVariable String name) throws IOException {	//응답은 응답라인, 헤더, 바디로 구성돼 있는데, 이 모든걸 갖고있는 객체가 ResponseEntity이고, Element로는 바디에 꽂아야할 데이터인 <Resource>
		Resource imageFile = imageFolder.createRelative(name);
		if(!imageFile.exists()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s 없다고!!", name));
		}
		
		//헤더에 ContentLength와 ContentDisposition 셋팅
		HttpHeaders headers = new HttpHeaders();
		headers.setContentLength(imageFile.contentLength());
		headers.setContentDisposition(ContentDisposition
										.attachment()
										.filename(name, Charset.forName("utf-8"))
										.build());
		
		//응답을 담당하는 ResponseEntity 리턴
		return ResponseEntity.ok()	//응답의 라인(상태코드 Ok)
							.headers(headers)	//헤더
							.body(imageFile);	//바디
	}
	
	@PostMapping
	public String uploadFile(@RequestPart MultipartFile[] uploadFile) throws IOException {
		File imageFolderFile = imageFolder.getFile();
		Arrays.stream(uploadFile)
				.filter(f -> ! f.isEmpty())
				.forEach(Failable.asConsumer(f->{	//람다식 안에서 예외 던지기위해 필요한 commons-lang3의 Failable
					File newFile = new File(imageFolderFile, f.getOriginalFilename());
					f.transferTo(newFile);
					
				}));
		
		return "redirect:/mission/file";
		
	}
}
