
		/*
			data-init-value 속성으로부터 엘리먼트 초기값 로딩
			data-* 속성의 키값 규칙성, 카멜 표기법을 표현할때 하이픈으로 대체함
				ex) data-role : key(role)
					data-init-value : key(initValue)
		*/
		
		calForm.querySelectorAll("[name]").forEach((el, idx)=>{
		let name = el.name;
		calForm[name].value = el.dataset.initValue;
		});
	
	calForm.addEventListener("change", (event)=>{	//화살표함수와 익명함수의 this는 다르니 주의할것
		console.log(event);
		event.target.form.requestSubmit();
	});
	
// 	$(selector).on("click",function(){})정적 엘리먼트
// 	$(document).on("click",".control-a", function(){})동적 엘리먼트 : 이벤트 버블링 구조 활용
	
	//위의 a태그는 사실상 작동하지 않고, 여기를 통해 작동 (a태그가 폼을 전송한게 아니고, calForm이 post방식으로 전송한것.)
// 	document.querySelectorAll(".control-a").forEach((el, idx)=>{
		document.addEventListener("click", (event)=>{
			if(event.target.classList.contains("control-a")){
			console.log(event.target);
			let el = event.target; //control-a 클래스를 가진 요소
			calForm.year.value = el.dataset.year;
			calForm.month.value = el.dataset.month;
			calForm.requestSubmit();
			}
		});
// 	});
	
	
	calForm.addEventListener("submit", (event)=>{
		event.preventDefault();//calForm의 동기요청은 중단됨 (동기요청을 중단시킨 이유 : 비동기식 요청으로 바꾸기 위해)
		let url = event.target.action;
		let method = event.target.method;
		
		//서버에 폼의 쿼리스트링을 넘겨야하는데, 직렬화해서 넘겨야하므로 FormData API를 이용
		let formData = new FormData(calForm);
		let urlSearchParams = new URLSearchParams(formData);
		let fetchPromise = fetch(url, {
			method:method,
			headers:{
				"Content-type" : calForm.enctype
			}, body : urlSearchParams
		});
		fetchPromise.then((resp)=>{	//then 응답이 왔을때
			if(resp.ok){	//resp는 플래그값인 ok를 속성으로 갖고있음. 성공 응답일때 resolve함수에 의해 실행될부분
				return resp.text();
			}else{	//실패 응답일때
				throw new Error(`상태코드 \${resp.status}이 응답으로 전송됨`, {cause:resp}); //fetch의 reject함수에 의해 실행됨
			}
		}).then((html)=>{
			calArea.innerHTML = html;
		}).catch(err=>{
			console.log(err);
			console.log(err.cause);
		}).finally(()=>{
			
		});
	});
	
	calForm.requestSubmit();