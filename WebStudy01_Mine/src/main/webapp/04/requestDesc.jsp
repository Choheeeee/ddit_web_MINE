<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>HTTP REquest(요청) => HttpServletRequest 객체</h4>
<a href="?param1=asd&param2=23">A태그를 통한 GET 요청</a>
<form method="post">
	<input type="text" name="param1">
	<input type="text" name="param2">
	<button type="submit">POST 전송</button>

</form>
	<pre>
		web : 역할이 다른 client와 server가 content로 표현되는 메세지를 교환하는 공간
		client가 메세지를 서버에 보내는것 : request packing (편지지 + 편지봉투)
		1. Request Line (수신자 정보) : URL(URI) - 명사(자원에 대한 식별자), request Method - 동사(행위정보, CRUD)
			<%=request.getRequestURI() %>, <%=request.getMethod() %>
			request Method
				1) GET(default method, Read) : form을 형성하지 않은 일반 요청의 메서드
										 (클라이언트는 자원을 소유하고있지 않고, 모든자원은 서버에 있기때문에, 기본적으로 클라이언트는 서버에게 달라고 하는것으로부터 시작함)
				2) POST(Create) : 클라이언트가 서버로 새로운 자원을 등록하기 위한 전송방식
				3) PUT(Update) : 서버가 가진 자원의 모든 속성을 일괄적으로 수정하기 위한 요청
				4) PATCH(Update) : 서버가 가진 자원의 모든 속성을 선택적으로 일부만 수정하기 위한 요청
				5) DELETE : 서버의 자원 삭제
				6) OPTION : pre-flight 요청에 사용되는 메서드(서버마다 지원하는 메서드 방식이 다른데, 어떤걸 지원하는지 알아보기 위해 사전 전송을 해봄)
				7) HEAD : 차후 응답 전송시 body가 없는 line + header의 응답이 전송됨
				8) TRACE : 서버를 디버깅할 목적의 요청 메서드
				
		2. Request Header : 클라이언트와 현재 요청에 대한 부가 정보(meta data)
			String value = getHeader(String name)
			Enumeration&lt;String&gt; getHeadernames
			
			Accept로 시작하는 헤더 : Accept(response content type), Accept-Encoding(response Archive format), 
								Accept-Language (response language + country format - Locale정보) => 나(클라이언트)가 응답으로 받을 수 있는
			Content로 시작하는 헤더 : post메서드로 body의 content에 대한 메타 데이터
								Content-Type (request content type : MIME), Content-Length (request content length)
			User-Agent : 사용자의 OS, 랜더링 엔진, 브라우저의 종류
			
			<%=request.getHeader("Accept") %>
		3. Request Body (편지지) : Content Body = Message Body (POST요청시에만 형성됨)
			content length : <%=request.getContentLength() %>
			content type : <%=request.getContentType() %>
			<%=request.getQueryString() %>
		
		client가 server에게 보내는 의도적인 컨텐츠를 서버에서 확보하는 방법
			1. request parameter
				1) post request : body를 통해 전송됨 - request.getParameter()를 이용
				2) get requeest : request Line을 통해 url의 query string 형태로 전송 - request.getQueryString() 이용하거나, request.getParameter()를 이용
				
			2. multipart request : body(=post방식)를 통해 전송되고, body를 여러개의 part로 분리하여 전송됨 - request.getPart(), request.getParts() 를 주로 사용
			3. request payload : body(=post방식)를 통해 전송됨, JSON & XML - request.getInputStream()를 주로 사용
	</pre>
	
	<table>
		<thead>	
			<tr>
				<th>헤더이름</th>
				<th>헤더값</th>
			</tr>
		</thead>
		<tbody>
			<%
				//컬렉션 뷰 = Enumeration & Iterator (현재 컬렉션의 사이즈를 모를때와 순차적인 데이터가 아닐때 사용한다.)
				Enumeration<String> headerNames =  request.getHeaderNames();
				while(headerNames.hasMoreElements()){
					String name = (String) headerNames.nextElement();
					String value = request.getHeader(name);
					out.println(
						String.format("<tr><th>%s</th><td>%s</td></tr>", name, value)
							
					);
				}
			%>
		</tbody>
	</table>
</body>
</html>