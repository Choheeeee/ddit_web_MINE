<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<!-- ex) 3! = 6 -->
<!-- 1. operand(피연산자)라는 유일한 파라미터(1이상의 양수만 가능)를 입력받는다. (factorialForm.jsp를 이용) -->
<!-- 	유효하지 않는 파라미터에 대해서는 bad request를 응답으로 전송함 -->
<!-- 2. factorial.jsp를 이용해 파라미터를 받고 재귀호출(recursive call) 구조를 활용하여 팩토리얼 연산 처리. -->
<!-- 3. 해당 연산의 결과는 다음과 같은 메세지로 출력 -->
	<input type="radio" name="accept" value="json">json
	<input type="radio" name="accept" value="html" checked>html
	<form action="<%=request.getContextPath() %>/case2/factorial.do" method="get">
		<input type="number" min="1" name="operand" placeholder="3!">
		<button type="submit">전송</button>
	</form>	
	<div id="resultArea"></div>
<script type="text/javascript">
// 	function submitHandler(event){
// 		event.preventDefault();
		
// 		return false;
// 	}
	let successes = {
			json:function(resp){
				$resultArea.html(resp.expression);
			},
			html:function(resp){
				$resultArea.html(resp);
			}
	}
	
	let $resultArea = $(resultArea);
	$("form:first").on("submit", function(event){
		event.preventDefault();
		//여기서 this는 이벤트의 타겟 = 즉, form
		let url = this.action; //form의 action
		let method = this.method;	//form의 메서드 방식
		let queryString = $(this).serialize();
		console.log("serialized query string : ",queryString);
		let settings = {
				url:url,
				method:method,
				data:queryString,	//query string 형태의 문자열로 전환됨 (serealizing)
				error:function(jqXHR, status, err){
					
				}
		};
		
		settings.dataType = $("[name=accept]:checked").val() ?? "html";
		
		settings.success = successes[settings.dataType];
		
		$.ajax(settings);
		return false;
	});
</script>
</body>
</html>