package kr.or.ddit.servlet08;

import java.io.IOException;
import java.time.ZonedDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//요청을 servlet으로 받았다고 해서(=servle객체를 썼다고 해서) Model2가 아니고, 여전히 요청과 응답처리자가 같으면 Model1 방식
//여기선 요청도 servlet이 받고 응답도 여기에서 처리되어 나가므로 Model1방식임
//서블릿은 요청을 받고 요청을 처리하는 Controller역할만하고
//응답컨텐츠를 만드는건 View단의 책임이 돼야함(컨텐츠를 html로 내보내야한다면, View로 jsp가 효과적이고 컨텐츠를 json으로 내보내야한다면 java가 효과적임)
//컨트롤러는 req만 사용, view는 resp만 사용해야야 model2방식
@WebServlet("/08/serverTime.do")
public class ServerTimeServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setIntHeader("Refresh", 1);	//비동기 요청에서는 의미 없음
		
		ZonedDateTime model = ZonedDateTime.now();
//		Locale locale = req.getLocale();
		
		req.setAttribute("now", model);	//now가autoRequest.js에서 jsonObj.now로 쓰
		String view = "/jsonView.do";
		req.getRequestDispatcher(view).forward(req, resp);
	}

}
