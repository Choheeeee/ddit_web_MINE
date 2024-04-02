<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="kr.or.ddit.servlet05.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userAgent</title>
</head>
<body>
<!-- 클라이언트의 브라우저 종류를 확인하고, -->
<!-- 다음과 같은 메세지를 출력할 것 -->
<!-- <h4>당신의 브라우저는 "크롬 || 엣지 || 웨일 || 사파리"입니다.</h4> -->

<%
    String agent = request.getHeader("user-agent");
	agent = agent.toUpperCase();
	String messagePattern = "<h4>당신의 브라우저는 %s 입니다.</h4>";
	String browserName = null;
	
//case1
//     if (agent != null && agent.contains("WHALE")) {
//     	browserName = "웨일";
//     } else if (agent != null && agent.contains("EDG")) {
//     	browserName = "엣지";
//     } else if (agent != null && agent.contains("CHROME")){
//     	browserName = "크롬";
//     } else if (agent != null && agent.contains("SAFARI")){
//     	browserName = "사파리";
//     } else{
//     	browserName = "알수없는 브라우저";
//     }
    
//case2
// Map<String, String> browserType = new LinkedHashMap<>();
// browserType.put("EDG","엣지");
// browserType.put("WHALE","웨일");
// browserType.put("CHROME","크롬");
// browserType.put("SAFARI","사파리");
// browserType.put("ETC","기타");

// browserName = browserType.get("ETC");
// for(Entry<String, String> entry : browserType.entrySet()){
// 	if(agent.contains(entry.getKey())){
// 		browserName = entry.getValue();
// 		break;
// 	}
// }

//case3
// browserName = BrowserType.ETC.getBrowserName();
// for( BrowserType tmp : BrowserType.values() ){
// 	if(agent.contains(tmp.name())){
// 		browserName = tmp.getBrowserName();
// 		break;
// 	}
// }

//case4



browserName = BrowserType.findBrowserName(request.getHeader("user-agent"));
%>
<%=String.format(messagePattern, browserName) %>
<h4><%=BrowserType.findBrowserType(agent).getBrowserName() %></h4>
</body>
</html>