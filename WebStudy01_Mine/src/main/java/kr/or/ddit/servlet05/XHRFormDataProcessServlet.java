package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.MediaType;

@WebServlet("/05/formDataProcess.do")
public class XHRFormDataProcessServlet extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		req.getParameterMap().forEach((k,v)->{
			System.out.printf("%s : %s\n", k, Arrays.toString(v));
		});
		
		Map<String, String[]> parameterMap = req.getParameterMap();
		
		String html = parameterMap.entrySet().stream()
						.map((en)->String.format("%s : %s", en.getKey(), Arrays.toString(en.getValue())))	//엔트리로 새로운 객체인 h4테그를 만드니까 map()함수
						.collect(Collectors.joining("\n"));	//응답으로 내보낼 html태그 데이터
		
		resp.setContentType(MediaType.TEXT_HTML_VALUE);
		try(
				PrintWriter out = resp.getWriter();
		){
			out.print(html);
		}
		
	}

}
