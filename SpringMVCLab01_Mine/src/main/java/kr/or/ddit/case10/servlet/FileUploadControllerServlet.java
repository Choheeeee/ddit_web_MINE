package kr.or.ddit.case10.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import lombok.extern.slf4j.Slf4j;
//클라이언트가 서버에 파일을 업로드하면 서버가 어떻게 처리하는지
//@MultipartConfig 어노테이션을 사용하여 멀티파트 요청을 처리하고, Servlet API의 Part 객체를 이용하여 파일 업로드를 수행 - case1

//Part 한개를 객체로 캡슐화 한다. => Part API 이용하는데, 이 API는 서블릿 3.x대에 종속돼버림.
@Slf4j
@WebServlet("/case10/upload1")
@MultipartConfig //chunk들을 모아놓을 임시 저장소(파라미터와 파트를 식별해주는 설정)

//@MultipartConfig(		=>	위의 어노테이션처럼 속성을 생략하면, 해당 속성은 기본값으로 설정됨
//	    location = "/tmp", // 업로드된 파일을 저장할 임시 디렉토리 경로
//	    maxFileSize = 1024 * 1024 * 5, // 5MB까지 허용
//	    maxRequestSize = 1024 * 1024 * 10, // 요청 전체가 10MB를 넘지 않도록 허용
//	    fileSizeThreshold = 1024 * 1024 // 1MB 이상의 파일은 디스크에 저장
//	)
public class FileUploadControllerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "case10/uploadForm1";
		req.getRequestDispatcher("/WEB-INF/views/"+viewName+".jsp").forward(req, resp);
	}
	
	
	private File saveFolder = new File("d:/saveFiles"); //클라이언트가 업로드 한 파일을 저장할 서버의 파일시스템 => 클라이언트가 파라미터로 넘겨준 파일이 저장될 서버사이드 폴더
														//해당 경로에 이 디렉토리가 미리 생성돼있어야함!! 아니면 saveFolder.mkdir()을 통해 만들어 줘야함.
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploader = req.getParameter("uploader");
		String count = req.getParameter("count");
		Part uploadFile = req.getPart("uploadFile"); //클라이언트가 업로드한 파일
		log.info("uploader : {}",uploader);
		log.info("count : {}",count);
		
		
		//클라이언트로부터 업로드된 파일을 서버의 파일시스템에 저장하는 부분
		File saveFile = new File(saveFolder, uploadFile.getSubmittedFileName()); //클라이언트가 업로드한 파일의 원래 이름을 가져옴
																				// 이 File 객체는 서버의 파일시스템에 실제로 저장될 파일을 나타냄
		byte[] buffer = new byte[1024];
		FileOutputStream fos = new FileOutputStream(saveFile); // 업로드된 파일의 내용을 읽어와서 saveFile에 저장하는 과정
		try(
			InputStream is = uploadFile.getInputStream();
		){
			int position = -1;
			while((position=is.read(buffer)) != -1){
				fos.write(buffer, 0, position);
			}
		}
		log.info("uploadFile : {}",uploadFile);
	}
}
