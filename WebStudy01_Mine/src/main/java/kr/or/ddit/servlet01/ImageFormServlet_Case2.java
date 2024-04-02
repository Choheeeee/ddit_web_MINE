package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Case1과 달리, Case2는 자바8에서 새로 나온 람다식문법, Stream으로 코딩해보기	=> 리팩토링

/**
 * Java8의 새로운 문법
 * 1. Functional Interface : 인터페이스의 추상메서드가 하나인 경우	=>	람다 표현식으로 연결됨.
 * 2. lambda : (함수형 인터페이스의 대표 메소드 인자들...) -> {대표 메서드의 body}
 * 3. Optinal : nullPointerException을 피할 수 있는 메서드의 집합 API
 * 4. Stream : 집합객체인 컬렉션의 요소들에 대해 일련에 순차적인 접근 구조를 표현할 수 있는 API
 */
@WebServlet(value="/case2/imageForm.do", initParams = {@WebInitParam(name = "imageFolderPath", value ="D:/01.medias/images") })
public class ImageFormServlet_Case2 extends HttpServlet {
	private ServletContext application;	//싱글턴객체이므로 전역변수로 선언해도 됨
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html; charset=utf8");
		
		StringBuffer html = new StringBuffer();
		html.append("\n <html>                                                        ");
		html.append("\n <head>                                                        ");
		html.append("\n <meta charset=\"UTF-8\">                                        ");
		html.append("\n <title>Insert title here</title>                              ");
		html.append("\n </head>                                                       ");
		html.append("\n <body>                                                        ");
		html.append(String.format("\n <form action='%s/image.do'>", req.getContextPath()));
		html.append("\n 	<select name='image'>										");
		
		String imageFolderPath = getServletConfig().getInitParameter("imageFolderPath");
		File imageFolder = new File(imageFolderPath);
		File[] imageFiles = imageFolder.listFiles((d,n)-> //Case2처럼 이렇게 람다식 안쓰고, Case1처럼 익명객체로 써도 되는데 Syntax sugar를 지향하므로 자바8 이후부턴 람다식을 쓴다.
			Optional.ofNullable(application.getMimeType(n))
					.filter((m)-> m.startsWith("image/"))
					.isPresent()
		);
		
		//자바8 이후 문법의 Stream : 컬렉션 (= 집합객체)이 있을때, 이걸 구성하는 요소들에 순차적으로 접근하고 싶을때 Stream을 사용 => 반복문과 비슷
//		Stream.of(imageFiles)
//				.forEach(f->{
//					html.append(String.format("\n<option value='%1$s'>%1$s</option>", f.getName()));
//					
//				});
		
		//imageFiles라는 파일 목록에 대해 스트림을 이용하여 각 파일에 대한 HTML <option> 태그 문자열을 생성하고, 그 결과를 줄 바꿈(\n)으로 구분하여 결합하는 작업을 수행
		String options = Stream.of(imageFiles)	//Stream.of(imageFiles): imageFiles 배열을 순차적이고 연속적인 스트림으로 변환합니다.
								.map(f->String.format("<option value='%1$s'>%1$s</option>", f.getName()))	//매핑함수
								.collect(Collectors.joining("\n"));	//각 생성된 문자열을 모아서 하나의 문자열로 결합하고, option태그 사이사이에 /n(= 라인피드)로 구분(마우스 우클릭, 소스보기)
		//결론 : imageFiles 배열의 각 파일에 대해 HTML <option> 태그를 생성하고, 그 결과를 줄 바꿈으로 구분하여 하나의 문자열로 반환
		
		html.append(options);
		html.append("\n 	</select>                                                   ");
		html.append("\n 	<input type='submit' value='이미지줘!!'>                    ");
		html.append("\n </form>                                                       ");
		html.append("\n </body>                                                       ");
		html.append("\n </html>                                                       ");
		
		try(
		PrintWriter out = resp.getWriter();
		){
		out.println(html);
		}
	}
}
