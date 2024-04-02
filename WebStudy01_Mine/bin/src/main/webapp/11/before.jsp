<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	LocalDateTime processTime = LocalDateTime.now();

	//Map형태의 저장데이터를 이용한 case1 & case2
	//case 1 - state less
	request.setAttribute("processTime", processTime);
	
	//case 2 - state full(Session 객체 이용)
	session.setAttribute("processTime", processTime);
	
	//case3 - state full(Cookie 객체 이용)
	Cookie customCookie = new Cookie("processTime", processTime.toString());
	response.addCookie(customCookie);
%>
<a href="afterCase1.jsp">이후 발생하는 새로운 요청(request scope)</a>
<a href="afterCase2.jsp">이후 발생하는 새로운 요청(session scope)</a>
<a href="afterCase3.jsp">이후 발생하는 새로운 요청(cookie scope)</a>

</body>
</html>