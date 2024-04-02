package kr.or.ddit.servlet02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Model1 = 서블릿 하나로 응답과 요청을 같이 처리함
//Model2 = 템플릿과 데이터를 분리한 구조 => ImageFormServlet_Case3과 C41Servlet 총 2개의 서블릿이 동작함

@WebServlet("*.c41") //경로매핑, 확장자매핑(경로는 중요하지않고, 확장자가 중요함 => 경로매핑과 확장자매핑은 같이 쓸 수 없음)을 줌으로써 특정파일만 지칭되지 않음
public class C41Servlet extends HttpServlet {
	private ServletContext application;	//싱글턴객체이므로 전역변수로 선언해도 됨
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 만들어내기
		resp.setContentType("text/html; charset=utf8");
		
		//템플릿 읽기
		StringBuffer html = new StringBuffer();
		
		try {
			String templateSource = readTemplate(req);
		
			//데이터와 템플릿파일을 합치기
			html.append(mergeDataAndTemplate(req, templateSource));
			
			try(
					PrintWriter out = resp.getWriter()
					){
				out.println(html);
			}
		}catch (FileNotFoundException e){
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
		}
		
	}

	private String mergeDataAndTemplate(HttpServletRequest req, String templateSource) {
		//Collection View = Enumeration, Iterator (Set 컬렉션에 접근방법을 제공함)
		Enumeration<String> names = req.getAttributeNames();
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();
			templateSource  = templateSource.replace("#"+key+"#", req.getAttribute(key).toString());
			
		}
		return templateSource;
	}

	private String readTemplate(HttpServletRequest req) throws FileNotFoundException, IOException, ServletException {
		///WebStudy01/01/sample.c41 => 01/sample을 정규식을 통해 추출해야함
		
		String tmplUrl = req.getRequestURI();
		
		System.out.printf("servlet path : %s\n", req.getServletPath());
		System.out.printf("servlet URI : %s\n", req.getRequestURI());
		
		String contextPath = getServletContext().getContextPath();
		
		//[대괄호]는 1글자에 대한 패턴, +는 한글자가 반복되는 패턴 => 정규식을 만든 후 이제 패턴매칭을 해야함 
		String regex = contextPath + "([\\w_/]+)()()" + "\\.c41";
		
		//Pattern객체는 생성자를 사용하지않고, 팩토리메서드를 통해 객체를 얻음
		Pattern regexp = Pattern.compile(regex);
		Matcher matcher = regexp.matcher(tmplUrl);
		
		
		if(matcher.find()) {
			String filePathPart = matcher.group(1);
			tmplUrl = filePathPart + ".c41";
			String tmplFSPath = getServletContext().getRealPath(tmplUrl); //파일 시스템상의 절대경로가 필요함(D:/~~~~/01/imageForm.c41)가 들어있음
			File templateFile = new File(tmplFSPath);
			
			if(!templateFile.exists()) {
				throw new FileNotFoundException(String.format("%s 파일 없다.", tmplUrl));
			}
			
			try(
					FileReader reader = new FileReader(templateFile);	//기본스트림
					BufferedReader br = new BufferedReader(reader);		//보조스트림
					){
				String tmp = null;
				StringBuffer template = new StringBuffer();
				while((tmp = br.readLine()) != null) {
					template.append(tmp);
					template.append("\n");
				}
				return template.toString();
			}//try end
		}else{
			throw new ServletException("정규식을 파싱해서 c41 파일의 위치를 찾는 과정에서 예외 발생");
		}//if(matcher.find()) end
		
	}
}
