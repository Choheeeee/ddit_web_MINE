<!-- Model1구조 -> 비동기요청방식의 Model1 구조 -> Model2 구조 -->
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.Month"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.YearMonth"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.stream.Stream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/calendar.css">

</head>
<body>
<%
	Locale locale = request.getLocale();
	YearMonth thisMonth = YearMonth.now();
%>
<!-- 요청은 폼을 통해 전송되고 있고, 폼태그를 통해 섭밋하면 동기식으로만 요청함, 그래서 폼태그의 기본기능인 동기식요청을 막아야함  -->
<form action="calendar.jsp" id="calForm" method="post">
	<input type="number" name="year" placeholder="년도" data-init-value="<%=thisMonth.getYear()%>">
	<select name="month" data-init-value="<%=thisMonth.getMonthValue()%>">
		<option value>월</option>
		<%=
			Stream.of(Month.values())
				.map(m->String.format("<option value='%d'>%s</option>",
						m.getValue(), m.getDisplayName(TextStyle.FULL, locale)))	
				.collect(Collectors.joining("\n"))
		%>
	</select>
	<select name="locale" data-init-value="<%=locale.toLanguageTag()%>">
		<option value>로케일</option>
		<%=
			Arrays.stream(Locale.getAvailableLocales())
					.map(l->String.format("<option value='%s'>%s</option>", l.toLanguageTag(), l.getDisplayName(l)))
					.collect(Collectors.joining("\n"))
		%>
	</select>
</form>
<div id="calArea">

</div>

<script src="<%=request.getContextPath() %>/resources/js/app/06/case2/calendarForm.js" type="text/javascript"></script>
</body>
</html>