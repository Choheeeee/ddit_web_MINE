package kr.or.ddit.servlet01;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 클라이언트가 /now.do 라는 요청을 보냈을때,
 * 현재 서버의 시각을 <h4>태그로 보여주는 컨텐츠를 구성할 것
 */
@WebServlet("/now_case1.do")	//클라이언트가 이 서블릿에게 명령을 내릴때 사용하는 주소
public class NowServlet_Case1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String html = "<html>" + "<body>" + "<h4>"+ new Date() +"</h4>" + "</body>" + "</html>";
		resp.getWriter().print(html);
	}
}




/*
 	여기 1개의 서블릿 안에서 데이터도 만들고 html도 하고있음 = 모델1구조
	모델 1구조를 잘 사용하지 않는 이유 
		SOLID(=객체지향설계)
		1.단일 책임 원칙
 */

