<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" trimDirectiveWhitespaces="false" %>	<%--page지시자가 갖는 속성 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/standard.jsp</title>
</head>
<body>
<h4>JSP(Java Server Page - 화면 하나를 구성하는 페이지를 위한 템플릿)</h4>
<h4>모델 : <%=request.getAttribute("sampleAttr") %></h4>
<pre>
	: 서블릿의 하위 스펙으로 동작하는 템플릿 엔진(View later) <%--(JSP는 서블릿의 하위스펙이므로 JSP를 구성하고있는 메인 언어는 자바인것) --%> 
	
	소스 구성 요소(토큰의 종류)
	1. 정적 요소(=Front-End 요소) : HTML, CSS, JavaScript... 정적 텍스트 <%-- 소스가 변하지 않음, 응답데이터에 그대로 출력됨--%>
	2. 동적 요소(=Back-End 요소, 백그라운드 요소)
		1) scriptlet : <% //java code %>
			<%
				String sample = "TEST"; //지역변수 이면서 블럭변수
				
			%>
		2) directiv (지시자) : <%--@ --%>	
			page(required) : jsp페이지에 대한 구체적인 설정을 표현(속성으로 제어)
			taglib(optinal) : 커스텀 태그 로딩할때 사용 <%-- 지금까지 우리는 이미 만들어져있는 html태그만 사용했지만, 개발자가 필요에따라 만든 html태그를 사용할때 taglib을 사용 --%> 
			include(optinal) : 정적 include를 통해 2개 이상의 jsp소스를 하나로 합칠때 사용
		3) expression (표현식) : <%=sample %>	<%=LocalDate.now() %> <%--값을 출력(=표현)할때 사용함--%>
		4) declaration (선언부) :  - 전역변수나 메서드 선언에 사용
		<%!
		private StringBuffer globalBuffer;
		void test(){
			
		}
		%>
		5) comment : <%-- JSP주석 --%>, <!-- HTML주석 -->
			<script type="text/javascript">
				//javascript 주석
			</script>
			<style type="text/css">
				/* css 주석 */
			</style>
			- client side(Front-End) : HTML, JS, CSS 주석
			- server side(Back-Edn) : Java, JSP 주석
		6) EL(Expression Language)
		7) JSTL
		8) JSP의 기본객체(내장객체) <%-- JSP도 서블릿이므로 session등 서블릿이 갖는 객체를 기본적으로 가짐. --%>
		
			ex) Tomcat
			1. WAS (Web Application Server) : 로직의 실행으로 생성되는 동적 컨텐츠를 서비스하는 어플리케이션 서버
				WS(Web Server) : 이미지 파일, 동영상 파일, css, js html등의 정적자원 서버
			2. Middle Tier (N-tier 구조에서 사용) : client와 raw데이터 서버 사이의 어플리케이션이 운영되는 중간 티어
			3. Servlet container : 컨테이너는 컴포넌트의 라이프싸이클 관리자를 가리키므로, 여기선 서블릿의 생명주기 관리자를 의미함. 
									서블릿의 싱글턴 인스턴스를 생성하고, 해당 요청이 발생한 경우 그 요청을 처리 할 수 있는 callback 메서드를 실행함
			4. JSP container : JSP의 생명주기 관리자를 의미하고, *.jsp템플릿 파일을 파싱하고, .java파일의 서블릿를 생성한 후, 해당 서블릿을 .class파일로 컴파일함
								그 후, 사실 JSP도 서블릿스펙이므로, 해당 서블릿의 싱글턴 인스턴스를 생성하고 요청 발생시 콜백메서드를 실행함.
</pre>
</body>
</html>