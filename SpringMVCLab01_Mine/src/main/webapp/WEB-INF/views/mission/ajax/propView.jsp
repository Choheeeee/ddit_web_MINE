<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="radio" name="contentType" value="application/x-form-urlencoded"/>parameter
<input type="radio" name="contentType" value="application/json"/>json
<form method="post" id="insertForm">
	<input type="text" name="propertyName" required placeholder="propertyName">
	<input type="text" name="propertyValue" required placeholder="propertyValue">
	<input type="text" name="description" placeholder="description">
	<input type="date" name="propDate"  placeholder="propDate">
	<input type="datetime-local" name="propTimestamp" placeholder="propTimestamp">
	<button type="submit">전송</button>
</form>

<script type="text/javascript">

	insertForm.addEventListener("submit", (event)=>{
		event.preventDefault();	
		let form = event.target;
		
		//[name="contentType"]:checked 이 객체가 있으면, 있다는 의미이니까 이 객체의 value를 사용하고, 없을땐 디폴트값인 form.enctype 사용
		let contentType = document.querySelector('[name="contentType"]:checked')?.value ?? form.enctype;
		
		
		let url = form.action;
		let formData = new FormData(form); 
		let body = null;
		
		if(contentType.indexOf("json") >= 0){
			let data = {};
			for(let k of formData.keys()){
				data[k] = formData.get(k)
			}
			body = JSON.stringify(data);
		}else{
			body = new URLSearchParams(formData).toString() /* key:value로 돼있는 폼데이터들을 쿼리스트링으로 만들어줌 */
		}
		
		let options = {
			method:form.method
			, headers:{
				"content-type":contentType,	
				"accept":"application/json"		
			},
			body:body
		};
		fetch(url, options)
			.then(resp => {
				if(resp.ok){
					return resp.json();	//서버에서 온 resp를 js로 언마샬링함
				}else{
					throw new Error(`\${url} 처리 중 에러 발생`, {cause:resp}); 
				}
			}).then(jsonObj=>{
				console.log(jsonObj);
			}).catch(err => console.error(err));
	});
</script>

<!-- 타겟 결정 : 폼에대한 이벤트 => form에 id부여 -->
<!-- 이벤트 종류 : 클릭 -->
<!-- 핸들러 함수 구현 -->
<!-- 핸들러를 타겟에 부착 -->

<!-- 1. 등록의 모든 절차는 비동기로 처리할 것. -->
<!-- 2. 등록에 사용되는 모든 컨텐츠는 json으로 전송하고 수신할것. (RequestBody, ResponseBody 이용) -->
<!-- 3. contentType 라디오버튼에 따라 전송 컨텐츠의 종류가 달라지게 구현할것. -->

</body>
</html>