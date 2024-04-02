<%@page import="java.io.UnsupportedEncodingException"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Optional"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/stateFull.jsp</title>
</head>
<body>
<h4>HTTP (ConnnectLess, StateLess) 보완하고, 상태를 유지하기(StateFull)위한 저장 구조</h4>
<pre>
	1. session : 상태유지를 위한 정보가 서버측에 저장되는 개념
		세션 (HttpSession 객체)
			database -> connectFull ( connection == session (통로의 의미) )
			http -> connectLess : 시간(웹앱을 사용하기 시작하는 순간 ~ 종료이벤트가 발생할때 까지의 기간)의 의미로 세션을 표현함
		
		세션의 생명주기
			생성 : 한 클라이언트가 사용하고 있는 하나의 에이전트(브라우저)에 대해 최초의 요청이 발생했을때 세션이 생성됨.
				  각 세션은 식별자로 세션 아이디를 발급받음
				  세션 아이디 : <%=session.getId() %>
				  세션의 생성 시점 : <%=new Date(session.getCreationTime())%>
			유지방법 : 서버와 클라이언트가 동일한 세션 아이디를 공유하는 상황 (= tracking mode)
				세션  timeout : <%=session.getMaxInactiveInterval() %>
					<%
						session.setMaxInactiveInterval(4*60);
					%>
				세션  timeout : <%=session.getMaxInactiveInterval() %>
				세션  마지막 요청 시점 : <%=new Date( session.getMaxInactiveInterval() ) %>
				1) COOKIE : request/response 헤더를 통해 세션 아이디 공유
				2) URL : request Line의 queryString을 통해 세션 아이디 공유(보안 취약함)
					<a href="stateFull.jsp;jsessionid=<%=session.getId()%>">세션 파라미터를 통한 세션 유지</a>
				3) SSL :Secure Socket Layer (= https)를 통해 암호화 전송구조를 이용한 공유
			소멸 : 만료시간(timeout) 이내에 새로운 요청이 발생하지 않는 경우
				1) 브라우저 종료
				2) 쿠키 삭제
				3) 직접 로그아웃 (Session Invalidate)
				<%
// 					session.invalidate();
					
				%>
	2. cookie : 상태유지를 위한 정보가 클라이언트측에 저장되는 개념
		(Cookie객체를 사용하는 단계)
		1) 쿠키 객체 생성 : 쿠키객체는 기본새성자가 없음. name/value 속성 필수, value에 한글을 비롯한 특수문자가 포함된 경우 -> UTLEncoding과정이 필요함
		2) 쿠키의 부가 속성 설정
			- name/value (required)
			- domain : 생략한 경우, 쿠키를 생성할때 도메인이 반영됨
						쿠키의 재전송 여부를 결정하는 조건
						ex) www.naver.com : 도메인은 레벨이 있는데, www.naver.com을 대상으로 한 요청에만 쿠키가 재전송됨
								.naver.com : *.naver.com을 대상으로 한 요청에만 쿠키가 재전송됨
						
			- path : 생략한 경우, 쿠키를 생성할때 경로가 반영됨
					쿠키의 재전송 여부를 결정하는 조건
					ex) / : 특정 도메인 이후의 모든 경로로 발생한 요청에 재전송됨
							request.getContextPath() : 특정 도메인의 해당 컨텍스트로 발생한 요청에만 재전송됨.
			- maxAge : 쿠키의 저장 시한 결정(초단위)
						ex) 일주일 : 60 * 60 * 24 7
							0 : 쿠키를 저장하지 말고, 저장돼있는 쿠키도 삭제한다는 의미
							-1 : 브라우저 종료시 쿠키 삭제
			- httpOnly : 클라이언트 사이드 모듈에서의 접근을 허용하지 않을때
			- secure : SSL(https) 프로토콜에서만 쿠키를 재전송하는 구조
		3) response.addCookie : 서버에서 만든 쿠키를 Response Header(set-cookie)를 통해 클라이언트단에 전송
		
		4) 클라이언트는 응답에 포함된 쿠키를 꺼내 브라우저의 쿠키 저장소에 저장
		5) 클라이언트는 다음번 요청에 쿠키를 다시 Request Header(cookie)를 통해 서버로 재전송함
		6) 요청에 포함된 쿠키를 확보하고, 상태를 복원 : request.getCookies() - 백단에서 복원할수도, 프론트단에서 복원할 수도 있음
		<%
// 			String encodedValue = URLEncoder.encode("한글값","utf-8");
// 			Cookie firstCookie = new Cookie("firstCookie",encodedValue);
// 			response.addCookie(firstCookie);  
			
			String firstCookieValue = Optional.ofNullable(request.getCookies())
												.map(ca->Arrays.stream(ca))
												.orElse(Stream.empty())
												.filter(c->"firstCookie".equals(c.getName()))
												.findFirst()
												.map(c->{
													try{
														return URLDecoder.decode(c.getValue(), "utf-8");
													}catch(UnsupportedEncodingException e){
														throw new RuntimeException(e);
													}
												})
												.orElse(null);
					
					
		%>
		상태를 복원한 firstCookie 값 : <%=firstCookieValue %>
</pre>
</body>
</html>