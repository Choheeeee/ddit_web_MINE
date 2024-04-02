<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/includee/preScript.jsp"/> <%--css 부트스트랩 --%>

</head>
<body data-context-path="<%=request.getContextPath()%>">
<%
	String contentPage = (String) request.getAttribute("contentPage");
%>
<jsp:include page="<%=contentPage %>"/> <%--실제 View --%>
<jsp:include page="/WEB-INF/includee/postScript.jsp"/> <%--jQuery --%>
</body>
</html>