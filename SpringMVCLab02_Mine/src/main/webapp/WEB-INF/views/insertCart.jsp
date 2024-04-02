<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>

<!-- 쿼리스트링 앞인 물음표앞에 이미 현재경로인 url이 생략돼있음. -->
<!-- 이 로케일설정은 옵셔널한데, 필요하다면 모든컨트롤러가 다 탈 수 있게 하는데 코드의 중복은 줄여야함 => 필터 기술 이용하는데, 필터는 WAS가 관리하고 같은 기능인 Interceptor는 Spring이 관리함. -->
<a href="?lang=ko">한글</a>
<a href="?lang=en">Eng</a>

	<!-- 문자기반의 인풋태그 4개와, 파일기반의 인풋태그 1개를 받아야하는데 섞이지 않도록, 바디를 쪼개야함. 그래서 multipart + 4개의 form의 name속성이있는 태그를 의미하므로 form-data -->
	<form:form method="post" modelAttribute="cart" enctype="multipart/form-data">	
		<input type="file" name="image">
		
		<form:input placeholder="cartMember" path="cartMember"/>
		<form:errors path="cartMember" element="span" />
		
		<form:input placeholder="cartNo" path="cartNo"/>
		<form:errors path="cartNo" element="span" />
		
		<form:input placeholder="cartProd" path="cartProd"/>
		<form:errors path="cartProd" element="span" />
		
		<form:input placeholder="cartQty" path="cartQty" type="number"/>
		<form:errors path="cartQty" element="span" />
		
		<form:button type="submit">전송</form:button>
	</form:form>
</body>
</html>