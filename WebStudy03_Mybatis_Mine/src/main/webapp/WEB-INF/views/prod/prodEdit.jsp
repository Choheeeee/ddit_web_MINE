<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<form method="post">
<table class="table table-bordered">
		<tr>
			<th>상품코드</th>
			<td>
				<input type="text" name="prodId" value="${prod.prodId }" readonly
					class="form-control"
				/>
				<span class="text-danger">${errors.prodId}</span>
			</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>
				<input type="text" name="prodName" 
					value="${prod.prodName}" class="form-control" />
				<span class="text-danger">${errors.prodName}</span>
			</td>
		</tr>
		<tr>
			<th>분류코드</th>
			<td>
				<select name="prodLgu" disabled>
					<option value="${prod.lprod.lprodGu }">${prod.lprod.lprodNm }</option>
				</select>
				<span class="text-danger">${errors.prodLgu}</span>
			</td>
		</tr>
		<tr>
			<th>제조사코드</th>
			<td>
				<select name="prodBuyer" disabled>
					<option value="${prod.buyer.buyerId }">${prod.buyer.buyerName }</option>
				</select>
				<span class="text-danger">${errors.prodBuyer}</span>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>
				<input type="number" name="prodCost" 
					value="${prod.prodCost}" class="form-control" />
				<span class="text-danger">${errors.prodCost}</span>
			</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>
				<input type="number" name="prodPrice" 
					value="${prod.prodPrice}" class="form-control" />
				<span class="text-danger">${errors.prodPrice}</span>
			</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>
				<input type="number" name="prodSale" 
					value="${prod.prodSale}" class="form-control" />
				<span class="text-danger">${errors.prodSale}</span>
			</td>
		</tr>
		<tr>
			<th>요약정보</th>
			<td>
				<input type="text" name="prodOutline" 
					value="${prod.prodOutline}" class="form-control" />
				<span class="text-danger">${errors.prodOutline}</span>
			</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>
				<textarea name="prodDetail" class="form-control">${prod.prodDetail }</textarea>
				<span class="text-danger">${errors.prodDetail}</span>
			</td>
		</tr>
		<tr>
			<th>이미지경로</th>
			<td>
				<input type="text" name="prodImg" 
					value="${prod.prodImg}" class="form-control" />
				<span class="text-danger">${errors.prodImg}</span>
			</td>
		</tr>
		<tr>
			<th>총재고</th>
			<td>
				<input type="number" name="prodTotalstock" 
					value="${prod.prodTotalstock}" class="form-control" />
				<span class="text-danger">${errors.prodTotalstock}</span>
			</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>
				<input type="number" name="prodProperstock" 
					value="${prod.prodProperstock}" class="form-control" />
				<span class="text-danger">${errors.prodProperstock}</span>
			</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>
				<input type="text" name="prodSize" 	value="${prod.prodSize}"
				class="form-control" />
				<span class="text-danger">${errors.prodSize}</span>
			</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>
				<input type="text" name="prodColor"
					value="${prod.prodColor}" class="form-control" />
				<span class="text-danger">${errors.prodColor}</span>
			</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>
				<input type="text" name="prodDelivery"
					value="${prod.prodDelivery}" class="form-control" />
				<span class="text-danger">${errors.prodDelivery}</span>
			</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>
				<input type="text" name="prodUnit" 	value="${prod.prodUnit}"
				class="form-control" />
				<span class="text-danger">${errors.prodUnit}</span>
			</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>
				<input type="number" name="prodQtyin"
					value="${prod.prodQtyin}" class="form-control" />
				<span class="text-danger">${errors.prodQtyin}</span>
			</td>
		</tr>
		<tr>
			<th>출고량</th>
			<td>
				<input type="number" name="prodQtysale"
					value="${prod.prodQtysale}" class="form-control" />
				<span class="text-danger">${errors.prodQtysale}</span>
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>
				<input type="number" name="prodMileage"
					value="${prod.prodMileage}" class="form-control" />
				<span class="text-danger">${errors.prodMileage}</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit" class="btn btn-primary">전송</button>
				<button type="reset" class="btn btn-danger">취소</button>
			</td>
		</tr>
	</table>
</form>


