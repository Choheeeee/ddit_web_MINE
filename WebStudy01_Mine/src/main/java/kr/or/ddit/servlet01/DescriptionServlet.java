package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 스펙 (두가지 50프로의 작업을 함 : 응답50프로, 요청 50프로)
 * : 웹상에서 발생하는 요청을 받고, 그에 대한 동적인 응답을 생성할 수 있는 객체의 요건 --> HttpServlet
 *
 *
 *	개발 단계
 *	1. HttpServlet 상속 클래스 구현
 *	2. do계열의 callback 메소드 재정의(특정 이벤트가 발생했을때 시스템이 자동으로 호출해주는 함수)
 * 		(서블릿이 갖고있는 메서드는 대부분이 콜백베서드이고, doGet() 역시 콜백메서드이고 웹상에서 Get이 발생했을때 톰캣에의해 호출됨)
 * 		서블릿의 callback 메서드 종류
 * 			1) lifecycle callback : init, destroy - 컨테이너가 기본적으로 싱글턴으로 객체를 관리하므로 어플리케이션 내에서 한번 실행됨.
 * 					init : 특별한 설정(loadOnStartup)이 없는한 최초의 요청이 발생하면 실행됨
 * 			2) request callback : service, do계열의 메서드
 * 					service() : 컨테이너인 톰켓에 의해 직접 호출되는 callback메서드 (요청 메서드와 무관하게 서버를 동작시키고 싶을땐 service()를 사용하고, 메서드방식이 특정되었을땐 doGet/doPost 등을 사용)
 * 					do계열() : service()내에서 현재 요청의 method(Get인지 Post인지)를 판단후 실행되는 분기 메서드 
 * 	3. 서블릿 등록 (톰캣이 서블릿을 호출할 수 있도록 정보를 알려줘야함)
 * 		web.xml : servlet -> servlet-name(simple name) like Alias, servlet-class(qualified name)
 * 	4. 서블릿 매핑
 * 		web.xml : servlet-mapping -> servlet-name, url-pattern
 * 
 * 	서버가 맨첨에 구동될때 web.xml을 딱 한번만 읽는다. 수시로 읽으면 과부하가 걸리기때문에. 따라서 web.xml이 변경되었을때 서버를 다시 재구동 해줘야함
 * 
 * 	5. 서버 재구동
 *
 *** 서블릿 스펙에서 제공되는 객체의 종류
 *	1. HttpServletRequest : 클라이언트와 해당 클라이언트가 서버에 보낸 요청에대한 모든 정보를 캡슐화한 객체
 *	2. HttpServletResponse : 서버가 클라이언트에게 응답해주는 모든 정보를 캡슐화한 객체
 *			ex) response data가 아닌 response content와 content metadata(response header)를 전송(data와 content의 차이는 MIME이 있냐 없냐의 차이)
 *	3. ServletConfig : 하나의 서블릿에 대한 설정 정보(configuration)를 캡슐화한 객체로, 서블릿 하나당 인스턴스가 하나씩 존재함.
 *	4. ServletContext : 현재 실행중인 어플리케이션과 해당 어플리케이션을 운영하고 있는 서블릿들을 운영하고 있는 서버에대한 정보를 캡슐화한 객체로, 어플리케이션 내에 하나의 싱글턴 인스턴스만 존재함
 *	5. HttpSession : 로그인정보를 유지할때 쓰는 객체 (하나의 클라이언트 혹은 하나의 에이전트(=브라우저)에 대한 고유정보를 캡슐화한 객체)
 */
public class DescriptionServlet extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {	//서버가 런칭된 후, 최초의 요청이 들어왔을때 딱 1번만 호출됨 (해당 서블릿의 인스턴스를 싱글턴패턴으로 구현해, 돌려막기 하고있음)
		super.init(config);
		String value = config.getInitParameter("dummy");
		System.out.printf("%s 초기화 되었음., param value : %s \n", this.getClass().getName(), value);
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("==========================================================================");
		System.out.println("super.service 이전 코드");
		super.service(req, resp);	//톰캣은 service()메서드를 호출하고, service()는 do계열메서드를 호출함(콜백함수인 do계열 메서드를 호출하는 주체는 톰캣이 아닌 service() 였던것)
		System.out.println("super.service 이후 코드");
		System.out.println("==========================================================================");
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 메서드 실행");
		resp.getWriter().println("desc servlet");
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.printf("%s 소멸 되었음.", this.getClass().getName());
	}
}
