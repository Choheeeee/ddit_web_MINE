package kr.or.ddit.property.servlet09;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.property.service.PropertyService;
import kr.or.ddit.property.service.PropertyServiceImpl;
import kr.or.ddit.vo.PropertyVO;
//case1 : model2, MVC패턴
//case2 : 모델링 구조, 레이어드 아키텍쳐 적용 => 어떻게 책임이 더 잘게 쪼개질 수 있는지 (상당부분의 책임이 다오로 넘어감. 파일로딩 등등)
	//데이터가 서비스되는 3단계 

//ui는 갱신의 대상이 아니다. 데이터만 동적으로 바뀌게 해야한다. = calculate서블렛 5번의 방식

/**
 * RESTful URI 구조 표현 : 명사(자원의 URI)와 동사(method)를 구분한다.
 * 	/09/property GET : 전체 조회
 * /09/property/fail.common.msg GET : 1건 조회
 * /09/property POST : 전체 수정
 * /09/propery/fail.common.msg PUT : 한건 수정
 *
 */
@WebServlet({"/09/property","/09/property/*"}) //RESTfull URI : 1개의 uri로 CRUD 4가지 작업을 함
public class PropertiesControllerServlet extends HttpServlet {	//Model2
	
	//의존관계 : PropertiesControllerServlet클래스는  PropertyDAO를 사용하기 위해 관계를 설정해준것.
	private PropertyService service = new PropertyServiceImpl();
	
	//전체조회
	private int list(HttpServletRequest req) {
		//Function<PropertyVO, String> resolve  = PropertyVO::getPropertyName;
		Set<String> keySet = service.retrieveKeySet();
		req.setAttribute("keySet", keySet);
		return 200;
	
	}
	
	//단건조회
	private int single(String propertyName, HttpServletRequest req) {
		PropertyVO property = service.retrieveProperty(propertyName);
		int status = 200;
		if(property==null) { //propertyname으로 조회한 해당 데이터가 DB에 존재하지 않는다는 의미
			status = 404;
		}else {
			req.setAttribute("property", property);
		}
		return status;
	}
	
	@Override //Read작업 = 조회
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String propertyName = getPropertyName(req); //group() : 정규 표현식에 매칭된 부분 중 특정 그룹의 값을 반환하는데, "/09/property/" 다음에 오는 부분이 추출.
		
		int status;
		if(propertyName != null) { //find() : 정규 표현식에 매칭되는 부분을 찾고, 매칭되는 부분이 있으면 true를 반환하고, 없으면 false를 반환
			status = single(propertyName, req); //또한 매칭이 있을 경우, 단건 조회 요청을 의미하므로 single 메서드를 호출
		
		}else {
			status = list(req); //매칭이 없을 경우, 전체 조회 요청을 의미하므로 list 메서드를 호출
		}
		
		if(status==200) {
			String view = "/jsonView.do";
			req.getRequestDispatcher(view).forward(req, resp);
		}else {
			resp.sendError(status);
		}
	}

	@Override //Create = insert = 등록
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(
			InputStream is = req.getInputStream();
		){
			 PropertyVO newProp = new ObjectMapper().readValue(is, PropertyVO.class);
			 boolean success = service.createProperty(newProp);
			 
			 if(success) {
				 resp.sendRedirect(req.getContextPath() + "/09/property");
			 }else {
				 
			 }
		}
	}
	
	@Override //Update = 수정
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (
			InputStream is = req.getInputStream();
		) {
			PropertyVO modifyProp = new ObjectMapper().readValue(is, PropertyVO.class);
			boolean success = service.updateProperty(modifyProp);
			req.setAttribute("success", success);
			String view = "/jsonView.do";
			req.getRequestDispatcher(view).forward(req, resp);
			
		}
	}
	
	private String getPropertyName(HttpServletRequest req) throws UnsupportedEncodingException {
		String propertyName = null;
		String requestURI = req.getRequestURI(); 
		
		String regex = "\\S*/09/property/(\\S+)";
		Pattern ptrn = Pattern.compile(regex);
		Matcher matcher = ptrn.matcher(requestURI);
		
		if(matcher.find()) {
			propertyName = URLDecoder.decode(matcher.group(1),"utf-8");
		}
		return propertyName;
	}
	
	@Override //Delete = 삭제  /경로변수 이용
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String propertyName = getPropertyName(req);
		if(propertyName != null) {
			boolean success = service.deleteProperty(propertyName);	//경로변수
			req.setAttribute("success", success);
			String view = "/jsonView.do";
			req.getRequestDispatcher(view).forward(req, resp);
		}else {
			resp.sendError(400);
		}
	}
}
