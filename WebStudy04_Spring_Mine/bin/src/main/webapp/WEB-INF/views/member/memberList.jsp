<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<table class="table table-bordered text-center">
	<thead>
		<tr>
			<th>일련번호</th>
			<th>회원명</th>
			<th>이메일</th>
			<th>휴대폰</th>
			<th>주소1</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty memberList }">
				<c:forEach items="${memberList }" var="member">
					<tr>
						<td>${member.rnum }</td>
						<td>${member.memName }</td>
						<td>${member.memMail }</td>
						<td>${member.memHp }</td>
						<td>${member.memAdd1 }</td>
						<td>${member.memMileage }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6">조건에 맞는 회원이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<%-- form 커스텀테그를 뭔가를 등록하거나 수정할때만 사용하는게 아니고, value속성에 초기값을 셋팅할때 필요함. --%>
				<form:form id="submitForm" modelAttribute="paging" method="get" class="border border-danger m-3 p-3">
					<h6>전송UI(Hidden Form)</h6>
					<input type="text" name="page" placeholder="page"/>
					<!-- PaginationInfo안에있는 simpleCondition. 또 그 심플컨디션 안에 searchType가 들어있음. -->
					<form:input path="simpleCondition.searchType" placeholder="searchType"/>	<!-- paging.simpleCondition.searchType paging이 생략된것/ 새로 랜더링될때, 기존 정보를 초기값으로 셋팅해주기위해 굳이 form:태그 사용 -->
					<form:input path="simpleCondition.searchWord" placeholder="searchWord"/>
				</form:form>
				<div data-pg-role="searchUI" data-pg-target="#submitForm" class="m-3 p-3 border border-primary row justify-content-center">
					<h6>입력UI</h6>
					<div class="col-auto">
						<form:select path="paging.simpleCondition.searchType"  class="form-select">
							<form:option value="" label="전체"/>
							<form:option value="name" label="이름"/>
							<form:option value="address" label="주소"/>
						</form:select>
					</div>
					<div class="col-auto">
						<form:input path="paging.simpleCondition.searchWord" class="form-control"/>
					</div>
					<div class="col-auto">
						<button type="button" data-pg-role="searchBtn" class="btn btn-primary">검색</button>
					</div>
				</div>
				${pagingHTML }
			</td>
		</tr>
	</tfoot>
</table>
<script src="<c:url value='/resources/js/app/common/paging.js'/>"></script>










