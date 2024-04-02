<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 문자1개와, jsp 4개를 넣을 수 있는 구멍 4개. 총 5개의 구멍 -->
<title><tiles:getAsString name="title"/></title>
<%-- <jsp:include page="/WEB-INF/includee/preScript.jsp" /> --%>
<tiles:insertAttribute name="preScript"/> <!--위의 jsp:include 를 그대로 대체함 / 각 구멍들을 구분하기 위해 식별자 name이 필요함. -->
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>

</head>
<body data-context-path="${pageContext.request.contextPath }">
<tiles:insertAttribute name="headerMenu"/>
<hr />
<%-- Thread name : <%=Thread.currentThread().getName() %>	<!-- 톰캣이 만들어준 쓰레드이며, 풀링정책을 운영하고 있어서 숫자가 10을 넘어가지 않음.  (콘솔에서 customSchedule이라고 나오는 쓰레드는 스프링이 만들어준 쓰레드)--> --%>
Thread name : ${threadName}
<hr/>

<tiles:insertAttribute name="contentPage"/>
<hr />

<tiles:insertAttribute name="postScript"/>
</body>
</html>

<!-- 
문제 : 모든 웹페이지에서 현재 쓰레드 이름을 모델로 사용하려고 함.
		모든 요청을 처리하는 과정에서 공통 모델을 생성해야함.
AOP를 적용해보자.

core concern : 모든 요청을 처리 -> 컨트롤러(target)
cross cutting concert : 공통 모델을 생성 -> advice
 -->












