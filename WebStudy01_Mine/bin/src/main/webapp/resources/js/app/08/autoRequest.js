document.addEventListener("DOMContentLoaded", ()=>{
	
	//주기와, 반복할 작업을 인자로 넣음
	//롱폴링방식으로 스케줄링메서드를 이욯해서 서버에 비동기요청을 보내는 방법 (Auto-Request 보내는 방법)
	setInterval(()=>{
		
		fetch("serverTime.do", {
			headers:{
				"Accept":"application/json",
			}
		}).then(resp=>{
			return resp.json();	//json() : 역직렬화 + 언마샬링을 한번에 해주는 메서드
		}).then(jsonObj=>timeArea.innerHTML = jsonObj.now);//jsonObj는 바로 위에서 역직렬화 + 언마샬링 된 ServerTimeServlet에서 forwad한 model 
	},1000);

});