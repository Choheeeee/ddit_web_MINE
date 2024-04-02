package kr.or.ddit.property.servlet09;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/09/propView.do")
public class PropViewControllerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contentPage = "/WEB-INF/views/09/propView.jsp";
		
		req.setAttribute("contentPage", contentPage);
		
		String view = "/WEB-INF/views/layout.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
		
	}
}
