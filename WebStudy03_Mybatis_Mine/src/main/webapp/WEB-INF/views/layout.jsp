<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/includee/preScript.jsp" />
</head>
<body data-context-path="${pageContext.request.contextPath }">
<jsp:include page="/WEB-INF/includee/headerMenu.jsp" />

<hr />

<jsp:include page="${contentPage }" />

<hr />

<jsp:include page="/WEB-INF/includee/postScript.jsp" />
</body>
</html>














