/*
	1. DOM(body를 포함) 트리 구조가 완성된 이후 실행되는 코드 = DOMContentLoaded이벤트가 필요함
	2. calForm submit 이벤트 중단
	3. 비동기 요청 전송
		line : action, method
		header : accept, content-type
		body : form's input, 바디를 넘기려면 직력화된 queryString data가 필요함
*/


document.addEventListener("DOMContentLoaded", ()=>{	//화살표함수를 쓸땐 this를 쓰면 안됨
	calForm.addEventListener("submit", (event)=>{
		event.preventDefault();
		let form = event.target;
		let url = calForm.action;
		let method = form.method;  
		let contentType = form.enctype;
		let accept = "text/html";
		let formData = new FormData(form); //FormData는 HTML 폼 요소의 데이터를 쉽게 수집하고 다룰 수 있도록, 폼 내의 모든 입력 요소의 이름과 값 쌍을 객화해서 담고있음
		let urlSearchParams = new URLSearchParams(formData); //FormData에서 얻은 데이터를 URL-encoded 문자열로 변환
		let fetchPromise = fetch(url, {method:method, headers:{"Content-Type":contentType, "Accept":accept}, body:urlSearchParams});
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