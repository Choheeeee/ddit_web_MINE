<%@page import="kr.or.ddit.servlet08.ServerTimeServlet"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>자원의 종류와 종류에 따른 식별 방법</h4>
<pre>
	자원의 위치와 접근 방법에 따라 3가지 분류.
		1.File system resource : 파일시스템상의 절대경로(물리경로)를 사용해서 접근 가능 = OS에 의해 운영되는 파일시스템
			ex) D:\01.medias\images\r1.jpg
			<%
				File fsREs = new File("D:\\01.medias\\images\\r1.jpg");
				out.println(fsREs.length());
			%>
		2.web resource : url이라 불리는 논리주소로 접근 가능 = 톰캣에 의해 운영되는 파일시스템
			ex) https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png
			ex) http://localhost/WebStudy01s/resources/images/r1.jpg
			ex) 우리의 workspace디렉토리 설정에 따라 여기가 바뀌는 주소{ /WebStudy01s/src/main/webapp} { /resources/images/r1.jpg } 바뀌지 않는 주소
			
			<%
				String logicalPath = "/resources/images/r1.jpg";
				String realPath = application.getRealPath(logicalPath);
				out.println("real path : " + realPath);
				File webRes = new File(realPath);
				out.println(webRes.length());
			%>
		3.classpath resource : classpath 이후의 경로부터 시작되는 qualified name의 형태로 접근 가능
			ex) kr/or/ddit/images/r2.jpg
			<% 
				logicalPath = "/kr/or/ddit/images/r2.jpg";
// 				logicalPath = "kr/or/ddit/images/r2.jpg"; - ClassLoader 를 쓸 때 맨앞에 슬래쉬가 빠져야함. classloader는 최상위부터 찾기때문에.
// 				ClassLoader classLoader = ClassLoader.getSystemClassLoader();
// 				ClassLoader classLoader = ServerTimeServlet.class.getClassLoader();
// 				URL url = classLoader.getResource(logicalPath);
				URL url = ServerTimeServlet.class.getResource(logicalPath);
				out.println(url);
				File cpRes = new File(url.toURI());
				out.println(cpRes.length());
			%>
		
</pre>

<h4>URI vs URL</h4>
URI(Uniform Resource Identifier) : 자원의 식별하는것
	URL(Uniform Resource Locator) : 범용자원을 식별할때 위치를 기준으로 식별 (자원을 식별할껀데 그중에서도 위치에 따라 식별하겠다는 의미)
	URN(Uniform Resource Naming) : 자원을 식별할때 명명된 이름으로 식별하는 방식(이름이 증복될수도 있고, 미리 이름을 정의해놓은 명부같은 시스템도 필요해서 비효율적)
	URC(Uniform Resource Content) : 자원을 식별할때 해당 자원이 가진 컨텐츠의 특성으로 식별하는 방식
	-> URN과 URC로 자원이 식별은 되겠지만, 유일성은 떨어짐 => 그래서 URL만 주로 사용함
	
	-> 절대경로(최상위부터 타고 들어가는 경로) : window.location객체가 갖고있는 속성들은 생략 가능(프로토콜, 호스트)
	
	client side : context path를 포함한 경로 형태
	server side : context path 이후의 경로만 사용
<img src="http://localhost/WebStudy01s/resources/images/r1.jpg">
<img src="//localhost/WebStudy01s/resources/images/r1.jpg">
<img src="/WebStudy01s/resources/images/r1.jpg">
<img src="<%=request.getContextPath() %>/resources/images/r1.jpg">

상대경로(브라우저 기준 - 브라우저에서 window.location의 href에서 나타내고있음) : 현재 문서의 출처를 기준으로 자원의 위치가 상대적으로 표현됨
<img src="../resources/images/r2.jpg">
</body>
</html>