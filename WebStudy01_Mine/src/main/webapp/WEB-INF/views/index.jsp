<%@ page contentType="text/html; charset=utf-8"%>
<main class="container-fluid">
<h4>웰컴 페이지</h4>
<%
	Object authId = session.getAttribute("authId");
	if(authId == null){
		%>
		<a href="<%=request.getContextPath() %>/login/loginForm.jsp">로그인</a>
		<%
	}else{
		%>
			<%=authId %>
			<form method="post" id="logoutForm" action="<%=request.getContextPath() %>/login/logOut.do"></form>	<%--히든폼 : 데이터입력이 목적이 아니고, 서버에 post로 데이터전송하는것이 목적임 --%>
			<a href="javascript:;" data-log-out="#logoutForm">로그아웃</a> <%--이 a태그를 클릭해도, 위의 form을 제출할 수 있게 두개를 묶어줘야함. id, data-log-out속성 이용--%>
		<%
	}
%>

</main>
<script src="<%=request.getContextPath() %>/resources/js/app/index.js"></script>