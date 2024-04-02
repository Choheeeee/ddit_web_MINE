<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.PropertyVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Driver"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<h4>JDBC(Java DataBase Connectivity)</h4>
	<pre>
		1. 드라이버를 빌드패스 (classpath)에 추가
		2. 드라이버 로딩 (driver class loading) : 드라이버 구현체(OracleDriver.class)가 상수풀에 로딩이 돼야함
		3. 드라이버를 통해 Connection 수립
		4. 쿼리 객체 (Statement) : 쿼리 컴파일, 쿼리 실행, DB에 전달, DB로부터 결과 받아옴
			- Statement
			- PreparedStatement (쿼리문을 미리 컴파일 해줌. 따라서, 쿼리문의 형태가 미리 고정됨)
			- CallableStatement
		5. 쿼리 작성(DDL, DML, TCL), 실행
			- ResultSet executeQuery
			- int executeUpdate
		6. 실행 결과 집합의 사용 : cursor 기반의 데이터(record, tuple)를 포인터를 통해 접근함.
		사용한 모든 자원의 release
	</pre>
	<%
		List<PropertyVO> propList = (List) request.getAttribute("propList");
	%>
	
<table class="table table-bordered">
	<thead>
		<tr>
			<th>PROPERTY_NAME</th>
			<th>PROPERTY_VALUE</th>
			<th>DESCRIPTION</th>
		</tr>
	</thead>
	<tbody>
		<%
			for(PropertyVO propVO : propList){
				%>
				<tr>
					<td><%=propVO.getPropertyName() %> </td>
					<td><%=propVO.getPropertyValue() %> </td>
					<td><%=propVO.getDescription() %> </td>
				</tr>
				<%
			}
		%>
	</tbody>
</table>
<script type="text/javascript">
	console.log($)
</script>
