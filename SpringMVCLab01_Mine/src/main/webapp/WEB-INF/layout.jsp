<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 문자1개와, jsp 4개를 넣을 수 있는 구멍 4개. 총 5개의 구멍 -->
<title><tiles:getAsString name="title"/></title>
<%-- <jsp:include page="/WEB-INF/includee/preScript.jsp" /> --%>
<tiles:insertAttribute name="preScript"/> <!--위의 jsp:include 를 그대로 대체함 / 각 구멍들을 구분하기 위해 식별자 name이 필요함. -->
</head>
<body data-context-path="${pageContext.request.contextPath }">
<tiles:insertAttribute name="headerMenu"/>

<hr />

<tiles:insertAttribute name="contentPage"/>
<hr />

<tiles:insertAttribute name="postScript"/>
</body>
</html>














