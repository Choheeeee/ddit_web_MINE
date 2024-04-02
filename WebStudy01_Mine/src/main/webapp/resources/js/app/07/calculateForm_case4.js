//json 요청 - html 응답
document.addEventListener("DOMContentLoaded", ()=>{	//화살표함수를 쓸땐 this를 쓰면 안됨
	calForm.addEventListener("submit", (event)=>{
		event.preventDefault();
		let form = event.target;
		let url = form.action;
		let method = form.method;
		
		let contentType = "application/json";
		
		let accept = "text/html";
		let formData = new FormData(form)
		let nativeData = {
			leftOp : parseFloat(formData.get("left")),
			rightOp :parseFloat(formData.get("right")),
			operatorType :formData.get("operator")
		};
		let jsonStr = JSON.stringify(nativeData);	 //마샬링 : js -> json
		
		let fetchPromise = fetch(url, {
			method:method, 
			headers:{"Content-Type":contentType, "Accept":accept}, body:jsonStr});
		
		fetchPromise.then(resp=>{
			if(resp.ok){
				return resp.text();
			}else{
				throw new Error(`상태코드 : ${resp.status} 발생`, {cause:resp});
			}
		}).then(text=>{
			resultArea.innerHTML = text;
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