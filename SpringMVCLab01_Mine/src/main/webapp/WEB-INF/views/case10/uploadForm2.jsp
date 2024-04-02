<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="fileVO" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFile">	<!-- 파일은 value를 셋팅해줄 필요가 없으므로 form:input구조를 안써도 됨 -->
		<form:errors path="uploadFile"/>
		<input type="file" name="uploadFile">
		<form:errors path="uploadFile"/>
		<form:input path="uploader"/>
		<form:errors path="uploader"/>
		<form:input type="number" path="count"/>
		<form:errors path="count"/>
		<button type="submit">전송</button>
	</form:form>
</body>
</html>