/**
 * 
 */
const cPath = document.body.dataset.contextPath; //전역 상수(layout.jsp 의 body에  data-context-path 숨겨놓음.)

document.addEventListener("DOMContentLoaded", ()=>{
	
	//지역 상수 : 아래에서 중복되는 함수의 래퍼런스를 받아놓음.
	const refreshFileArea = jsonObj => {	
		let ulTag = document.createElement("ul");
		//이미지의 이름 한개 => name, idx가 파라미터로 들어옴
		ulTag.innerHTML = jsonObj.map(n=>`
						<li><a href="${cPath}/mission/file/${n}">${n}</a></li>
					`).join("\n");
		document.querySelector("#fileArea").replaceChildren(ulTag);
	};
	
	fetch(location.href, {
		headers:{
			"accept":"application/json"
		}
	}).then(resp => {
		if(resp.ok){
			return resp.json()
		}else{
			throw new Error("요청 처리 과정에서 문제 발생", {cause:resp});
		}
	}).then(refreshFileArea).catch(err => console.error(err));
	
	//fetch()는 contentType을 생략하면 기본으로 multipart요청이 발생하므로, URLSearchParams() 즉, 쿼리스트링으로 안만들어줘도 됨.
	//그리고 이 post요청이 서버로 전송되면, 서버인 컨트롤러는 이미 @RequestPart MultipartFile[] uploadFile로 명시해놨음. 서버는 이미 multipart타입을 받을 준비가 돼있으므로, 쿼리스트링화 안하고 body:fomData만 넘긴다.
	fileForm.addEventListener("submit", (event)=>{
		event.preventDefault();
		let form = event.target;
		let formData = new FormData(form);
		fetch(form.action, {
			method:form.method,
			headers:{
				"accept":"application/json"
			},
			body:formData
		}).then(resp=>{
			if(resp.ok){
				return resp.json()
			}else{
				throw new Error("요청 처리 과정에서 문제 발생", {cause:resp});
			}
		}).then(refreshFileArea).catch(err => console.error(err));
		fileForm.querySelector("input").value = "";
	});
});