<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form method="post" modelAttribute="prod" enctype="multipart/form-data">

<form:hidden path="prodId"/>
<table class="table table-bordered">
		<tr>
			<th>상품명</th>
			<td>
				<form:input path="prodName" class="form-control" />
				<form:errors path="prodName" element="span" cssClass="text-danger"/>
			</td>
		</tr>
		<tr>
			<th>분류코드</th>
			<td>
				<form:select path="prodLgu">
					<form:option value="" label="분류선택"></form:option>
					<c:forEach items="${lprodList }" var="lprod">
						<form:option value="${lprod.lprodGu }" label="${lprod.lprodNm }" />
					</c:forEach>
				</form:select>
				<form:errors path="prodLgu" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>제조사코드</th>
			<td>
				<form:select path="prodBuyer">
					<form:option value="" label="제조사선택"/>
					<c:forEach items="${buyerList }" var="buyer">
						<form:option value="${buyer.buyerId }" cssClass="${buyer.buyerLgu }" label="${buyer.buyerName }"/>
					</c:forEach>
				</form:select>
				<form:errors path="prodBuyer" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>
				<form:input type="number" path="prodCost" cssClass="form-control" />
				<form:errors path="prodCost" element="span" cssClass="form-control"/>
			</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>
				<form:input type="number" path="prodPrice" cssClass="form-control" />
				<form:errors path="prodPrice" element="span" cssClass="text-danger"/>
			</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>
				<form:input type="number" path="prodSale" cssClass="form-control" />
				<form:errors element="span" cssClass="text-danger" path="prodSale"/>
			</td>
		</tr>
		<tr>
			<th>요약정보</th>
			<td>
				<form:input type="text" path="prodOutline" cssClass="form-control" />
				<form:errors element="span" cssClass="text-danger" path="prodOutline"/>
			</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>
				<form:input type="text" path="prodDetail" cssClass="form-control" />
				<form:errors element="span" cssClass="text-danger" path="prodDetail"/>
			</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td>
				<form:input type="file" path="prodImage" cssClass="form-control" />
				<form:errors element="span" cssClass="text-danger" path="prodImage"/>
			</td>
		</tr>
		<tr>
			<th>총재고</th>
			<td>
				<form:input type="number" path="prodTotalstock" cssClass="form-control" />
				<form:errors element="span" cssClass="text-danger" path="prodTotalstock"/>
			</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>
				<form:input type="number" path="prodProperstock" cssClass="form-control" />
				<form:errors element="span" cssClass="text-danger" path="prodProperstock"/>
			</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>
				<form:input type="text" path="prodSize" cssClass="form-control" />
				<form:errors element="span" cssClass="text-danger" path="prodSize"/>
			</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>
				<form:input type="text" path="prodColor" cssClass="form-control" />
				<form:errors element="span" cssClass="text-danger" path="prodColor"/>
			</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>
				<form:input type="text" path="prodDelivery" cssClass="form-control" />
				<form:errors element="span" cssClass="text-danger" path="prodDelivery"/>
			</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>
				<form:input type="text" path="prodUnit" cssClass="form-control" />
				<form:errors element="span" cssClass="text-danger" path="prodUnit"/>
			</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>
				<form:input type="number" path="prodQtyin" cssClass="form-control" />
				<form:errors element="span" cssClass="text-danger" path="prodQtyin"/>
			</td>
		</tr>
		<tr>
			<th>출고량</th>
			<td>
				<form:input type="number" path="prodQtysale" cssClass="form-control" />
				<form:errors element="span" cssClass="text-danger" path="prodQtysale"/>
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>
				<form:input type="number" path="prodMileage" cssClass="form-control" />
				<form:errors element="span" cssClass="text-danger" path="prodMileage"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<form:button type="submit" class="btn btn-primary">전송</form:button>
				<form:button type="reset" class="btn btn-danger">취소</form:button>
			</td>
		</tr>
	</table>
</form:form>
