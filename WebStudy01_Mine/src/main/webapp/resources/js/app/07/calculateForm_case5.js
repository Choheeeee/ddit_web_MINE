//json 요청 - json 응답
document.addEventListener("DOMContentLoaded", ()=>{	//화살표함수를 쓸땐 this를 쓰면 안됨
	calForm.addEventListener("submit", (event)=>{
		event.preventDefault();
		let form = event.target;
		let url = calForm.action;
		let method = form.method;
		
		let contentType = "application/json";
		
		let accept = "application/json";
		let formData = new FormData(form);
		let nativeData = {
			leftOp : parseFloat(formData.get("left")),
			rightOp :parseFloat(formData.get("right")),
			operatorType :formData.get("operator")
		};
		
		let jsonStr = JSON.stringify(nativeData); //stringify() : JS객체를 JSON화 함 => JSON화 해서 서버에 요청을 보내야함
		
		let fetchPromise = fetch(url, {
			method:method, 
			headers:{"Content-Type":contentType, "Accept":accept}, body:jsonStr
		});	//셋팅한 정보로 요청을 비동기적으로 보냄
		
		//비동기로 보낸 요청에 응답이 옴
		fetchPromise.then(resp=>{	//서버로부터 온 응답이 성공응답이면,
			if(resp.ok){
				return resp.json();	//서버의 응답을 json형태로 파싱하기
			}else{
				throw new Error(`상태코드 : ${resp.status} 발생`, {cause:resp});
			}
		}).then(jsonObj =>{
			resultArea.innerHTML = jsonObj.expression;
		}).catch(err=>{
			
			console.log(err.message);
			if(err.cause){
				let resp = err.cause;
				resp.text().then(ep=>resultArea.innerHTML=ep);
			}
		});
		return false;
	});
});