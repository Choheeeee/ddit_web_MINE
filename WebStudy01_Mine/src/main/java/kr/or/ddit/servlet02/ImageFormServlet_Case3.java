package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//@WebServlet(value="/case3/imageForm.do", initParams = {@WebInitParam(name = "imageFolderPath", value ="D:/01.medias/images") }) 
//=> 이 경로가 바뀌면 이 경로를 바라보고있는 모든 서블릿들의 경로도 변경해줘야 하므로 결합력을 낮춰야함 -> web.xml에 context-param 등록해주기

//템플릿엔진을 사용해서, 데이터와 ui를 분리하고 그 분리한걸 어떻게 merge해서 브라우저에 출력해낼 수 있는지 Case3과 imageForm.c41에서 구현함 => 스파게티 코딩을 막고, JSP가 작동하는 원리를 구현해봄.
//request객체엔 map이 들어있음 = request scope

@WebServlet("/case3/imageForm.do")
public class ImageFormServlet_Case3 extends HttpServlet {
	private ServletContext application;	//싱글턴객체이므로 전역변수로 선언해도 됨
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 만들어내기
		resp.setContentType("text/html; charset=utf8");
		
		String imageFolderPath = application.getInitParameter("imageFolderPath");
		File imageFolder = new File(imageFolderPath);
		
		File[] imageFiles = imageFolder.listFiles((d,n)-> //Case2처럼 이렇게 람다식 안쓰고, Case1처럼 익명객체로 써도 되는데 Syntax sugar를 지향하므로 자바8 이후부턴 람다식을 쓴다.
			Optional.ofNullable(application.getMimeType(n))
					.filter((m)-> m.startsWith("image/"))
					.isPresent()
		);
		
		//만든 데이터를 req에 넣음
		String cPath = req.getContextPath();
		
		//imageFiles라는 파일 목록에 대해 스트림을 이용하여 각 파일에 대한 HTML <option> 태그 문자열을 생성하고, 그 결과를 줄 바꿈(\n)으로 구분하여 결합하는 작업을 수행
		String options = Stream.of(imageFiles)	//Stream.of(imageFiles): imageFiles 배열을 순차적이고 연속적인 스트림으로 변환합니다.
								.map(f->String.format("<option value='%1$s'>%1$s</option>", f.getName()))	//매핑함수
								.collect(Collectors.joining("\n"));	//각 생성된 문자열을 모아서 하나의 문자열로 결합하고, option태그 사이사이에 /n(= 라인피드)로 구분(마우스 우클릭, 소스보기)
		
		req.setAttribute("cPath", cPath);
		req.setAttribute("options", options);
		
		req.getRequestDispatcher("/01/imageFormJQ.c41").forward(req,resp);
	}
}
