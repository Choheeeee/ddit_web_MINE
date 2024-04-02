package kr.or.ddit.servlet01;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/now_case2.do")
public class NowServlet_Case2 extends HttpServlet {
     
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String은 상수이므로 읽기밖에 안되고, 아래의 5줄은 모두 다 다른 스트링 객체가 생성된것임.
		//따라서 /이렇게 +연산자를 쓰면 다 다른 문자열객체가 만들어짐. 이렇게 컨캣(결합)방식으로 코딩하면 상수풀의 메모리를 많이 잡아먹음
		String html = "<html>";
		html += "<body>";
		html += "<h4>"+ new Date() +"</h4>";
		html += "</body>";
		html += "</html>";
		response.getWriter().print(html);
	}

}
