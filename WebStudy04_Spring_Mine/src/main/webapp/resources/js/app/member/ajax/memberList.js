/**
 * 
 */

//document.addEventListener("DOMContentLoaded", ()=>{
	
	submitForm.addEventListener("submit", (event)=>{
		event.preventDefault();
		let form = submitForm;// event.target;
		let url = form.action
		let method = "GET";
		let listBody = document.getElementById("listBody");
		let pagingArea = document.getElementById("pagingArea");
//		let formData = new FormData(form);
//		let urlSearchParams = new URLSearchParams(formData);
//		console.log(urlSearchParams);
		
		fetch(url, {
			method:method
			,headers:{
				"Accept":"application/json"
			}
		}).then(resp => {
			if(resp.ok){
				return resp.json();
			}else{
				throw new Error(`\${url} 처리 중 에러 발생`, {cause:resp});
			}
		}).then(jsonObj=>{
			console.log(jsonObj);
			let memberList = jsonObj.memberList;
			let trtags = "";
			if(memberList.length > 0){
				memberList.map(member=>{
					trtags += `
						<tr>
							<td>${member.rnum}</td>
							<td><a href="javascript:fnOne('${member.memId}')">${member.memName}</a></td>
							<td>${member.memMail}</td>
							<td>${member.memHp}</td>
							<td>${member.memAdd1}</td>
							<td>${member.memMileage}</td>
						</tr>
					`
				});
				listBody.innerHTML = trtags;
				pagingArea.innerHTML = jsonObj.pagingHTML;
			}else{
				
			}
		}).catch(err => {
			console.error(err)
			
		}).finally(()=>{
			
		})
	});
	
	document.querySelector('[ data-pg-role="searchBtn"]').click();
//	submitForm.requestSubmit();	//vanilla script엔 submit()과 requestSubmit()이 있는데, submit()은 "submit"이벤트 객체를 만들지않고(=발생시키지 않고) form을 전송하고, requestSubmit()은 submit이벤트 객체를 만든 후(=submit 이벤트를 발생시키고)전송 요청을 보냄. 
								//그래서 이벤트리스너에 명시된submit 이벤트가 트리거 될 수 있었던것. addEventListener("submit" ()=>{});
								//반면에 jQuery엔 submit()밖에 없고, jQuery는 메서체이닝 구조 때문에 계속 jQuery객체가 반환된다. 그래서 jQuery를 쓸땐 그냥 submit()
								
