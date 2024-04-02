package kr.or.ddit.property.servlet09;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.property.service.PropertyService;
import kr.or.ddit.property.service.PropertyServiceImpl;
import kr.or.ddit.vo.PropertyVO;

//모델2로 바꾸기, 레이어드 구조 활용, 페이지 모듈화 - 레이아웃.jsp 재활용
@WebServlet("/12/jdbcDesc.do")
public class JdbcDescControllerServlet extends HttpServlet{
	
	private PropertyService service = new PropertyServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<PropertyVO> propList = service.retrieveProperties();
		
		req.setAttribute("propList", propList); //Model
		req.setAttribute("contentPage", "/WEB-INF/views/12/jdbcDesc.jsp"); //실제 View
		
		String view = "/WEB-INF/views/layout.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
	
}
