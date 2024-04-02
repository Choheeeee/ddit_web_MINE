<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 웹에서 파일을 업다운로드 하려면 패키지가 필요한데, 이 패키지의 역할을 part가 함. 여러개면 multipart -->
<!-- Multipart MIME TYPE : multipart가 의미하는건 바디를 여러개로 쪼갠다는 의미. 
	몇개로 쪼갤것인가?는 input의 name속성 갯수(id속성은 셀렉터역할만 하므로 꼭 name속성이 있어야 서버에 보낼수 있는 구조가 됨)
-->
<!-- part는 문자파트와 파일파트로 또 나뉘는데, filename으로 파트가 식별된다. -->
	<form method="post" enctype="multipart/form-data">	
		<input type="file" name="uploadFile">
		<input type="text" name="uploader">
		<input type="number" name="count">
		<button type="submit">전송</button>
	</form>
</body>
</html>