package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//single value annotation => 속성의 이름이 value일때만 가능	<-> multi value annotation

@WebServlet(value = "/image.do", loadOnStartup = 1, initParams = {@WebInitParam(name = "imageFolderPath", value ="D:/01.medias/images")}) 
public class ImageStreamingServlet extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String imageFolderPath = getServletConfig().getInitParameter("imageFolderPath");
		File imageFolder = new File(imageFolderPath);
		
		String imageName = req.getParameter("image");
		
		if(imageName == null || imageName.trim().isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}
		
		File imageFile = new File(imageFolderPath, imageName);
		
		if(!imageFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "그런 이미지 없음.");
			return;
		}
		
		
		//MIME타입은 출력스트림을 개방하기전에 설정해줘야함!!
		ServletContext application = getServletContext();	//모든 서블릿은 자기의 reference를 갖고있는데 톰캣도 서블릿이므로, 자신의 참조를 이용해서 하드코딩없이 MIME Type을 구할 수 있음.
		application.getMimeType(imageName);
//		String mime = "image/png";
//		resp.setContentType(mime);
		
		resp.setContentLengthLong(imageFile.length());
		
//		FileInputStream fis = null;
//		try {
//		fis = new FileInputStream(imageFile);
//		}finally {
//			if(fis != null)
//				fis.close();
//		}
		//try with resource 구문의 형태
		try(
				FileInputStream fis = new FileInputStream(imageFile);
				OutputStream os = resp.getOutputStream();
		){
			int buffer = -1;
			while((buffer = fis.read()) != -1) {	//EOF(End Of File = -1 = null) 마지막 문자까지 반복
				os.write(buffer);
			}
			os.flush();
		}
	}
}
