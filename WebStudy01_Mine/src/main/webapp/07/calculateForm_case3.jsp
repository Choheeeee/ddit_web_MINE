<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath() %>/resources/js/app/07/calculateForm_case3.js?<%=System.currentTimeMillis()%>">
</script>

</head>
<body>
	<input type="radio" name="accept" value="text/html" data-fn-name="fnHtml">HTML
	<input type="radio" name="accept" value="application/json" data-fn-name="fnJson">JSON
	<form  id="calForm" method="post" enctype="application/x-www-form-urlencoded" 
	action="<%=request.getContextPath() %>/07/case3/calculator.do">
		<input type="number" name="left" placeholder="좌측피연산자">
		<select name="operator">
			
			<%=
				Stream.of(OperatorType.values())	//operatorType의 값들을 option으로 만들어줘야하므로 map() 이용
					.map(i->String.format("<option value='%s' label='%c'/>", i.name(), i.getSign()))	//만들어진 option들을 모아서, 문자열들을 \n으로 구분
					.collect(Collectors.joining("\n"))
			%>
		</select>
		<input type="number" name="right" placeholder="우측피연산자">
		<button type="submit">=</button>
	</form>
	<div id="resultArea"></div>
</body>

</html>