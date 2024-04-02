/*
	1. DOM(body를 포함) 트리 구조가 완성된 이후 실행되는 코드 = 문서준비코드
		 1) document.addEventListener("DOMContentLoaded", ()=>{});
		 2) window.onload = function(){};
	2. calForm submit 이벤트 중단
	3. 비동기 요청 전송
		line : action, method
		header : accept, content-type
		body : form's input, 바디를 넘기려면 직력화된 queryString data가 필요함
*/
//case1 : parameter 전송 후 HTML응답 수신
//case2 : parameter 전송 후 JSON응답 수신

// 36, 37라인 오류나니까 주석 해제후 서버 켤것!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

//resolve 함수
let fnOwner ={
	fnHtml:function(html){
		resultArea.innerHTML = html;
	},
	fnJson:function(jsonObj){
		resultArea.innerHTML = jsonObj.calculator.expression;
	}
}

document.addEventListener("DOMContentLoaded", ()=>{	//화살표함수를 쓸땐 this를 쓰면 안됨

	calForm.addEventListener("submit", (event)=>{
		event.preventDefault();
		let form = event.target;
		let url = calForm.action;
		let method = form.method;
		let contentType = form.enctype;
		
		let acceptRdo = document.querySelector("[name='accept']:checked");
		
		//Optional 체이닝 ?, null병합연산자 ("text/html" 가 default)
		let accept = acceptRdo?.value ?? "text/html";  //acceptRdo변수 즉, 선택된 버튼이 있고 그 버튼의 value 속성이 존재하면 그 값을 반환하고, 그렇지 않으면 "text/html"을 반환
		let fnName = acceptRdo?.dataset.fnName ?? "fnHtml";
		
		let formData = new FormData(form);
		let urlSearchParams = new URLSearchParams(formData);
		
		
		let fetchPromise = fetch(url, {method:method, headers:{"Content-Type":contentType, "Accept":accept}, body:urlSearchParams});
		
		
		//then 메서드는 fetchPromise에 할당된 Promise 객체가 성공적으로 이행(resolve)될 때 실행되는 콜백 함수를 등록하는 부분
		//resp는 promise를 실행하고 서버가 돌려준 응답이 매개변수로 옴
		fetchPromise.then(resp=>{
			if(resp.ok){
				let respContentType = resp.headers.get("Content-Type");
				if(respContentType.indexOf("json") > 0){
					return resp.json(); //json() 내부적으로 언마샬링을 해주는 메서드, json() 메서드는 Promise를 반환하며, 
					
				}else{
					return resp.text();
				}
									//파싱이 완료되면 해당 Promise가 이행되어 JSON으로 파싱된 데이터를 반환
			}else{
				throw new Error(`상태코드 : ${resp.status} 발생`, {cause:resp});
			}
		}).then(fnOwner[fnName]).catch(err=>{
			if(err.cause){
				let resp = err.cause;
				resp.text().then(ep=>resultArea.innerHTML=ep);
			}
		});
		return false;
	});
	
}); //DOMContentLoaded 종료