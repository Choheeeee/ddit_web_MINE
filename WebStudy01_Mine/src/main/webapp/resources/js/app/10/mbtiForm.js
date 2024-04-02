/*
1. 페이지가 완성되면,
2. 비동기요청(/10/mbti)을 전송하고,
3. json컨텐츠를 수신한 후,
4. select의 option태그를 동적 구성함.
*/

//function resolve(){ 선언식
//	
//};
//var resolve = function(){ 표현식 => 함수도 하나의 객체이고, 그 래퍼런스를 변수에 담아놓을 수 있는 구조
//	
//};
//개발방법론 : EDD(Event Driven Development) - 이벤트 핸들러 이용, TDD(Test Driven Development) - JUnit
var fnRresolveEntrySet = (jsonObj)=>{
	let mapArray = jsonObj.entrySet;
	let options = "";
	options += mapArray.map((associativeArray)=>{	//16개 엔트리 반환
		console.log(associativeArray);
		let option = "";
		for(let propName in associativeArray){	//한번 실행되는 for문
			let entryKey = propName
			let entryValue = associativeArray[entryKey]
			option += `<option value="${entryKey}">${entryValue}</option>`;
		}
		return option;
	}).join("\n");
	document.querySelector("[name=type]").innerHTML = options;
};

var findCookie = (name) => {
	let matches = document.cookie.match(`${name}=([^;]+)`);
	return matches ? matches[1] : undefined;
};

const select = document.querySelector("[name=type]")
var fnRresolveEntryMap = (jsonObj)=>{
	let associativeArray = jsonObj.entryMap;
	let options = "";
	for(let propName in associativeArray){	//16번 실행되는 for문
			let entryKey = propName
			let entryValue = associativeArray[entryKey]
			options += `<option value="${entryKey}">${entryValue}</option>`;
	}
	select.innerHTML = options;
	
//	let savedType = findCookie("mbtiCookie");
	let savedType = select.dataset.initValue;
	if(savedType){
		select.value = savedType;
		select.form.requestSubmit();
	}
	
};

var fnOptionLoad = (event)=>{
	cPath = document.body.dataset.contextPath;
	fetch(`${cPath}/10/mbti`, {headers:{"accept":"application/json"}
	}).then((resp)=>{
		if(resp.ok){
			return resp.json();
		}else{
			throw new Error(`상태코드${resp.status}`,{cause:resp});
		}
	}).then(fnRresolveEntryMap).catch(err=>console.error(err));
};
	
var fnMbtiLoad = (event)=>{
	if(event.target.tagName !== "FORM") return false;
	
	let form = event.target;
	event.preventDefault();
	
	let formData = new FormData(form);
	let urlSearchParams = new URLSearchParams(formData);
	
	fetch(`${form.action}?${urlSearchParams}`, {method:form.method,headers:{"Accept":"text/html"
		},cache:"no-cache"
	}).then((resp)=>{
		if(resp.ok){
			return resp.text();
		}else{
			throw new Error(`상태코드${resp.status}`,{cause:resp});
		}
	}).then((html)=>{
		if(! window['resultArea']){
			let div = document.createElement("div");
			div.id = "resultArea";
			form.after(div);
		}
		let parser = new DOMParser
		let newDoc = parser.parseFromString(html,"text/html");
		let preTag = newDoc.body.innerHTML
		resultArea.innerHTML = preTag;
	}).catch(err=>console.error(err));
	return false;
};
	 
document.addEventListener("DOMContentLoaded", fnOptionLoad);

document.addEventListener("submit", fnMbtiLoad)
	
	

