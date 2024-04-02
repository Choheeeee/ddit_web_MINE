<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${not empty message }">
	<script>
		alert("${message}")
	</script>
</c:if>

<h2> ** SpringMVC tutorial ** </h2>
<h4>
	spring-webmvc 모듈 기반의  [Model2 아키텍처와 MVC 패턴 구조] 어플리케이션 구현
</h4>
<ul>
	<li>
		case1 : <a>컨트롤러 bean 등록과 핸들러 메소드의 request 매핑</a>
	</li>
	<li>
		case2 : <a>핸들러 메소드에서 request header[cookie] 처리</a>
	</li>
	<li>
		case3 : <a>컨트롤러에서 뷰를 사용하는 방법</a>
	</li>
	<li>
		case4 : <a>컨트롤러에서 뷰로 데이터 전달 방법(Scope 활용 케이스)</a>
	</li>
	<li>
		case5 : <a>컨트롤러에서 흐름 제어 방식(forward, redirect)</a>
	</li>
	<li>
		case6 : <a>핸들러 메소드에서 request parameter 처리</a>
	</li>
	<li>
		case7 : <a>form UI 구성과 데이터 바인딩(Command Object 의 활용)</a>
	</li>
	<li>
		case8 : <a>json 응답을 전송하는 방법</a>
	</li>
	<li>
		case9 : <a>json 요청을 수신하는 방법</a>
	</li>
	<li>
		case10 : <a>파일핸들링 (다운로드)</a>
	</li>
</ul>

<pre>
	1. @Controller 어노테이션으로 컨트롤러 객체를 빈으로 등록
	2. 핸들러 메소드 등록 및 구현
		@RequestMapping (@Get / @Post)으로 요청과 핸들러 메소드 연결
			- url, method, header..
	3. 핸들러 메소드 내에서 모델과 뷰에 대한 정보가 결정됨 =>  모든 핸들러는 POJO로 구현되므로, 시그니처에 제한이 없음
			- request에 포함된 모든 정보는 핸들러 메소드 구현시 메소드 아규먼트로 수신이 가능함
				@RequestHeader, @CookieValue, @RequestAttribute 등으로 수신할 수 있음.
			
			- 생성된 모델을 뷰로 전달하는 방법
				Model, ModelAndView 등의 객체를 이용하여 전달 -> 핸들러 메소드가 종료될때 해당 객체 내의 모든 attribute는 scope에 복사됨
			
			- 뷰의 결정 방법
				logical view name을 핸들러 메소드의 반환값으로 설정
				response의 content-type에 따른 뷰 결정 -> request accept헤더에 따라 스프링 내부에서 뷰가 자동으로 결정됨
</pre>
</body>
</html>