package kr.or.ddit.servlet01;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서비스를 하려면 : 컨텐츠와 컨텐츠에 대한 정보(메타데이터)를 제공해야함!!
@WebServlet("/now_case3.do")
public class NowServlet_Case3 extends HttpServlet {
     
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//resp.setContentLength(5);
		//MIME : 파일의 확장자로부터 파일의 종류를 유추하는 방식을 미디어 서비스의 전송 큰텐츠의 종류를 표현
		//마임타입 설정을 해줄 때 주의할 점 : 출력스트림을 개방하기 전에
		resp.setContentType("text/html; utf-8");
		StringBuffer html = new StringBuffer();
		html.append("<html>");
		html.append("<body>");
		html.append(String.format("<h4>오늘 날짜 : %s</h4>", LocalDate.now()));
		html.append(String.format("<h4>현재 시각 : %s</h4>", LocalDateTime.now()));
		html.append("</body>");
		html.append("</html>");
		resp.getWriter().print(html);
	}

}
