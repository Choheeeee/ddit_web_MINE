package kr.or.ddit.case10;

import java.io.IOException;
import java.nio.charset.Charset;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/case10")
//서버가 갖고있는 파일을 클라이언트에게 어떻게 서비스 할것인가 
// => 서버가 갖고있는 파일들은 서버의 파일시스템자원이지 웹자원이 아니기때문에 보호영역에 있음. 그래서 서버사이드에 있는 파일을 가져오기 위해 중개자 역할을 할 중개자 컨트롤러가 필요함
public class FileViewerAndDownloadController {
	
	@Value("file:D:/01.medias/images/") //images는 폴더임을 나타내주기 위해 마지막에 슬래쉬 추가
	private Resource imageFolder;
	
	
	//이미지 뷰어 = 인라인 구조 : 브라우저에 즉시 띄워 보여주기만 함
	@GetMapping(value = "fileView/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE) //OCTET = 8비트 = 1바이트
	@ResponseBody //응답내용은 JSON도 아니고, html도 아닌 Resource(=실제파일)임.
	public Resource fileService1(@PathVariable String fileName) throws IOException {
		Resource imageFile = imageFolder.createRelative(fileName); //실제 물리주소가 들어있음
		if(!imageFile.exists()) {
			//원래같으면 resp.sendErrorMessage() 이거나, 커스텀 예외를 만들어서 내보냈었음
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s 파일을 찾을 수 없음", fileName));
		}
		return imageFile; //서버의 디렉토리에 실제로 존재하는 파일이라면, 그 해당 파일을 ResponseBody에 바로 응답으로 리턴
	}
	
	//이미지를 첨부파일로 다운로드 = attachment 구조 : 로컬에 가져가서 저장하란 의미
	@GetMapping(value = "file/{fileName}")
	public ResponseEntity<Resource>  fileService2(@PathVariable String fileName) throws IOException {
//		Content-Disposition: attachment; filename="filename.jpg"
		
		Resource imageFile = imageFolder.createRelative(fileName); //실제 물리주소가 들어있음
		
		if(!imageFile.exists()) {
			//원래같으면 resp.sendError() 이거나, 커스텀 예외를 만들어서 내보냈었음
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s 파일을 찾을 수 없음", fileName));
		}
		
		//@ResponseBody대신에 ResponseEntity객체를 쓰는 이유는 response의 line, header, body를 커스터마이즈 하기 위함
		HttpHeaders headers = new HttpHeaders();
		headers.setContentLength(imageFile.contentLength());
		
		//inline 구조인지 attachment 구조인지를 설정하기 위해 ContentDisposition 객체 이용
		ContentDisposition disposition = ContentDisposition.attachment() //ContentDisposition은 디폴트로 inline이여서 브라우저에 보여주기만 하고, 우리가 attachment로 설정하면 첨부파일형태가 됨
						.filename(fileName, Charset.forName("utf-8"))
						.build();
		headers.setContentDisposition(disposition);
		
		return ResponseEntity.ok()
					.headers(headers)
					.body(imageFile);
	}
}
