/**
 * 
 */
document.addEventListener("DOMContentLoaded", ()=>{
	console.log(bootstrap);

	//hidden form 구조
	$("[data-log-out]").on("click", (event) => {
		let $aTag = $(event.target);
		let formSelector = $aTag.data("logOut");
		$(formSelector).submit();	//submit() : submit이벤트 발생
	});
	
});
