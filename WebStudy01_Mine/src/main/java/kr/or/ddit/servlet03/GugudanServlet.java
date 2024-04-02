package kr.or.ddit.servlet03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/case4/gugudan.do")	//요청 접수 => controller의 역할
public class GugudanServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String minParam = request.getParameter("minDan");
		String maxParam = request.getParameter("maxDan");
		int status = 200;
		int minDan = 2;	//JSP에 넘겨줘야할 데이터 = Model이 만들어짐
		if(minParam !=null && !minParam.trim().isEmpty()){
			try{
			minDan = Integer.parseInt(minParam);
			}catch(NumberFormatException e){
				status = 400;
			}
		}
		int maxDan = 13;	//JSP에 넘겨줘야 할 데이터 = Model이 만들어짐
		if(maxParam !=null && !maxParam.trim().isEmpty()){
			try{
			maxDan = Integer.parseInt(maxParam);
			}catch(NumberFormatException e){
				status = 400;
			}
		}
		
		if(status != 200){
			response.sendError(status);
			return;
		}
		
		//setAttribute()를 통해 JSP에 넘겨줄 데이터를 셋팅
		request.setAttribute("minDan", minDan);
		request.setAttribute("maxDan", maxDan);
		
		String view = "/WEB-INF/views/02/gugudan.jsp";
		
		//forward()를 통해 JSP에 위임
		request.getRequestDispatcher(view).forward(request, response);
	}
}
