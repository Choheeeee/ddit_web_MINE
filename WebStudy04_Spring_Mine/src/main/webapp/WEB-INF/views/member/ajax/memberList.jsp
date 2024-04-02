<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
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
	<tbody id="listBody">
	
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<form id="submitForm" 	class="border border-danger m-3 p-3">
					<h6>전송UI(Hidden Form)</h6>
					<input type="text" name="page" placeholder="page"/>
					<input name="simpleCondition.searchType" placeholder="searchType"/>	
					<input name="simpleCondition.searchWord" placeholder="searchWord"/>
				</form>
				<div data-pg-role="searchUI" data-pg-target="#submitForm" class="m-3 p-3 border border-primary row justify-content-center">
					<h6>입력UI</h6>
					<div class="col-auto">
						<select name="simpleCondition.searchType"  class="form-select">
							<option value="" label="전체"/>
							<option value="name" label="이름"/>
							<option value="address" label="주소"/>
						</select>
					</div>
					<div class="col-auto">
						<input name="simpleCondition.searchWord" class="form-control"/>
					</div>
					<div class="col-auto">
						<button type="button" data-pg-role="searchBtn" class="btn btn-primary">검색</button>
					</div>
				</div>
				<div id="pagingArea"></div>
			</td>
		</tr>
	</tfoot>
</table>
<script src="<c:url value='/resources/js/app/common/paging.js'/>"></script>
<script src="<c:url value='/resources/js/app/member/ajax/memberList.js'/>"></script>
