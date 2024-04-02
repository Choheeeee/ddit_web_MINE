//case1 : parameter 전송 후 HTML응답 수신
//case2 : parameter 전송 후 JSON응답 수신

document.addEventListener("DOMContentLoaded", ()=>{	//화살표함수를 쓸땐 this를 쓰면 안됨
	calForm.addEventListener("submit", (event)=>{
		event.preventDefault();
		let form = event.target;
		let url = calForm.action;
		let method = form.method;
		let contentType = form.enctype;
		let accept = "application/json";
		let formData = new FormData(form);
		let urlSearchParams = new URLSearchParams(formData);
		
		//여러 옵션들이 들어간 두 번째 인자(=요청정보)는 fetch() 함수가 반환하는 요청에대한 응답결과를 다루게될 객체인 Promise 객체를 생성할 때 사용된다
		//이 Promise는 네트워크 요청의 상태 및 응답결과를 비동기적으로 처리하기 위해 사용됨. 코드에서는 이 Promise객체를 fetchPromise 변수에 할당한것.
		let fetchPromise = fetch(url, {method:method, headers:{"Content-Type":contentType, "Accept":accept}, body:urlSearchParams});
		
		
		//then 메서드는 fetchPromise에 할당된 Promise 객체가 성공적으로 이행(resolve)될 때 실행되는 콜백 함수를 등록하는 부분
		//resp는 promise를 실행하고 서버가 돌려준 응답이 매개변수로 옴
		fetchPromise.then(resp=>{
			if(resp.ok){
				return resp.json(); //json() 내부적으로 언마샬링을 해주는 메서드, json() 메서드는 Promise를 반환하며, 
									//파싱이 완료되면 해당 Promise가 이행되어 JSON으로 파싱된 데이터를 반환
			}else{
				throw new Error(`상태코드 : ${resp.status} 발생`, {cause:resp});
			}
		}).then(jsonObj=>{
			console.log(jsonObj);
			resultArea.innerHTML = jsonObj.expression;
			
		}).catch(err=>{
			console.error(err.message);
			if(err.cause){
				let resp = err.cause;
				resp.text().then(ep=>resultArea.innerHTML=ep);
			}
		});
		return false;
	});
});