/*
요청의 목적 : 서버사이드의 자원에 대해 어떤 행위를 하기 위함
	자원의 식별(명사) : URI
	어떤 행위(동사) : request method
	이걸 구현하기 위한 필수 패러다임 : 주고받는 자원은 데이터(json/xml)만 주고받는다. (데이터를 꾸미는 html태그 둥은 필요x)
RESTful URI 구조 표현 : 명사(URI)와 동사(method)를 구분한다.
	1. document 완성 이벤트 처리
	2. 비동기 요청
	3. json content 수신
	4. ul 태그 구성
*/
const cPath = document.body.dataset.contextPath;
const baseURI = `${cPath}/09/property`;

function fnCommFetch(url, options, fnResolve) {
    $.ajax({
        url: url,
        method: options.method,
        data: options.body,
        dataType: 'json',
        success: fnResolve,
        error: function (jqXHR, textStatus, errorThrown) {
            console.error(`상태코드 ${jqXHR.status} 수신`, { cause: jqXHR });
        }
    });
}

function fnCommFetch(url, options, fnResolve){
	fetch(url, options)
		.then(resp=>{
			if(resp.ok){
				return resp.json()
			}else{
				throw new Error(`상태코드 ${resp.status} 수신`, {cause:resp});
			}
		}).then(fnResolve).catch(err=>console.error(err));
}

var fnRefreshDataLiTags = jsonObj=>{	//json에서 언마샬링된 JS객체가 들어옴
		let keyArray = jsonObj.keySet; //jsonObj는 마샬링&역직렬화 된 후 들어온 객체니까 다시 배열로 원복된 상태
		let ulTag = document.createElement("ul");
		document.body.append(ulTag);
		let liTags = keyArray.map(k=>
		`<li class="list-group-item">
		<span class="delBtn">삭제</span>
		<span class="property-name" data-bs-toggle="modal" data-bs-target="#exampleModal">${k}</span>
		</li>
		`).join("\n");
		ulTag.innerHTML = liTags;
		ulTag.classList.add("list-group");
		
}

var fnReadProperties = (event)=>{
	fnCommFetch(baseURI, {
		headers:{
			"Accept":"application/json"
		}
	},fnRefreshDataLiTags);
};


var fnReadProperty = (event)=>{
	if(! event.relatedTarget.classList.contains("property-name")) return false; //!을 붙이면 <span class="property-name"> 영역울 click한게 아니고, 다른데를 클릭했다는 의미
	
	let propertyNameTag = event.relatedTarget;
	let propertyName = propertyNameTag.innerHTML;
	let modal = event.target;
	
	fnCommFetch(`${baseURI}/${propertyName}`,{
		headrs: {
			"Accept" : "application/json"
		}
	}, jsonObj=>{
		let property = jsonObj.property;
		for(let n in property){
			updateForm[n].value = property[n];
		}
		
//		updateForm.propertyValue.value = property.propertyValue;
//		updateForm.description.value = property.description;
	});
	document.querySelector("li.active")?.classList.toggle("active")
	propertyNameTag.parentElement.classList.toggle("active");
};

const formToObject = (form)=>{
	let data = {};
	let formData = new FormData(form);
	
	for(let n of formData.keys()){
		data[n] = formData.get(n);
	}
	return data;
};

var fnAddProperty = (event)=>{
	event.preventDefault();
	let form = event.target;
	let data = formToObject(form);
	let body = JSON.stringify(data);
	fnCommFetch(baseURI, {
			method:"post",
			headers:{
				"accept":"application/json",
				"content-type":"application/json"
				}, body:body
	}, jsonObj=>{
		document.querySelector("ul").remove();
		fnRefreshDataLiTags(jsonObj);
		form.reset();
	});
	return false;
}

var fnRemoveProperty = (event)=>{
	if(!event.target.classList.contains("delBtn")) return false;
	
	let propertyNameTag = event.target.nextElementSibling;
	let propertyName = propertyNameTag.innerHTML;
	
	var input = confirm(`${propertyName} 지울거야?`);
   	if (! input) return false;
	
	fnCommFetch(`${baseURI}/${propertyName}`,{
		method:"delete",
		headers:{
			"accept":"application/json"
		}
	}, jsonObj=>{
		if(jsonObj.success){
			propertyNameTag.parentElement.remove();
		}
	});
	
};

var fnModifyProperty = (event)=>{
	event.preventDefault();
	let form = event.target;
	let data = formToObject(form);
	let body = JSON.stringify(data);
	fnCommFetch(`${baseURI}/${data.propertyName}`, {
			method:"put",
			headers:{
				"accept":"application/json",
				"content-type":"application/json"
				}, body:body
	}, jsonObj=>{
		if(jsonObj.success){
			let modalInstance = bootstrap.Modal.getInstance(exampleModal);
			modalInstance.hide();
			let modalToggler = document.querySelector("li.active").children[1];
			setTimeout(()=>{
				modalInstance.show(modalToggler);
			}, 1000);
		}
	});
	return false;
}

document.addEventListener("DOMContentLoaded",fnReadProperties);

document.addEventListener("show.bs.modal", fnReadProperty);

document.addEventListener("hidden.bs.modal", (event)=>{
	event.target.querySelector("form")?.reset();
});

insertForm.addEventListener("submit",fnAddProperty);

updateForm.addEventListener("submit",fnModifyProperty);

document.addEventListener("click",fnRemoveProperty);