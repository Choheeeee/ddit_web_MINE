<%@page import="java.util.Arrays"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.time.Month"%>
<%@page import="java.util.Optional"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.DayOfWeek"%>
<%@page import="java.time.temporal.WeekFields"%>
<%@page import="java.time.YearMonth"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.ZonedDateTime"%>
<%@page import="java.time.format.FormatStyle"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Model1구조 -> 비동기요청방식의 Model1 구조 -> Model2 구조 -->
<%
	Locale locale = Optional.ofNullable(request.getParameter("locale"))
							.filter(lp -> !lp.trim().isEmpty())
							.map(lp -> Locale.forLanguageTag(lp))
							.orElse(Locale.getDefault());

	ZoneId zone = ZoneId.systemDefault();	//여기서 말하는 system은 JVM
	
	ZonedDateTime current = ZonedDateTime.now(zone);
	FormatStyle fullStyle = FormatStyle.FULL;
	
	
	int targetYear = Optional.ofNullable(request.getParameter("year"))
							.map(yp->new Integer(yp))
							.orElse(YearMonth.from(current).getYear());
	YearMonth thisMonth =  Optional.ofNullable(request.getParameter("month"))
							.map(mp->YearMonth.of(targetYear, Integer.parseInt(mp)))
							.orElse(YearMonth.from(current));
	
	
	YearMonth beforeMonth = thisMonth.minusMonths(1);
	YearMonth nextMonth = thisMonth.plusMonths(1);
	
	
	WeekFields weekFields = WeekFields.of(locale);
	DayOfWeek firstDOW = weekFields.getFirstDayOfWeek();
	LocalDate firstDate = thisMonth.atDay(1);	//11월1일
	int firstDateDOW = firstDate.get(weekFields.dayOfWeek());	//요일을 정수로 표현
	int offset = firstDateDOW -1 ; //걸쳐져있는 전달의 날짜가 몇개인지


%>

<!-- 비동기요청 페이지 처리 -->
<h4><%=current.format(DateTimeFormatter.ofLocalizedDateTime(fullStyle, fullStyle).withLocale(locale)) %></h4>
<h4>

<!-- 더미스크립트 : 링크는 이동하지 않고, 클릭만 살려두기 => UI에 데이터를 숨겨두기 위해 -->
<a class="control-a" href="javascript:;" data-year="<%=beforeMonth.getYear() %>" data-month="<%=beforeMonth.getMonthValue()%>">전 달</a> 

<%=thisMonth.format(DateTimeFormatter.ofPattern("yyyy, MMMM", locale)) %>

<a class="control-a" href="javascript:;" data-year="<%=nextMonth.getYear() %>" data-month="<%=nextMonth.getMonthValue()%>">다음달</a> 

</h4>

<table class="clz1 clz2">
<%
out.println("<thead>");
for(int col = 0; col < 7; col++){
	DayOfWeek tmp = firstDOW.plus(col); 	//7개의 요일 상수
	out.println(
			String.format("<th>%s</th>", tmp.getDisplayName(TextStyle.FULL, locale))
	);
}
out.println("</thead>");
out.println("<tbody>");
LocalDate tmpDate =  firstDate.minusDays(offset);
for(int row = 1; row <= 6; row++){
	out.println("<tr>");
	for(int col = 1; col <= 7; col++){
		YearMonth tmpMonth = YearMonth.from(tmpDate);
		String clz = tmpMonth.isBefore(thisMonth) ? "before" : 
						tmpMonth.isAfter(thisMonth) ? "after" : "this-month";
		clz += " " + tmpDate.getDayOfWeek().name().toLowerCase();
		out.println(
			String.format("<td class='%2$s'>%1$d</td>", tmpDate.getDayOfMonth(), clz)
		);
		tmpDate = tmpDate.plusDays(1);
	}
	out.println("</tr>");
}
out.println("</tbody>");
%>
</table>

</body>
</html>