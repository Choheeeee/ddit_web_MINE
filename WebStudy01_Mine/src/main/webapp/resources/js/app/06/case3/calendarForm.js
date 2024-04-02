
$(function(){
   let $inputs = $(calForm).find(":input[name]");
   $inputs.each((idx, el)=>{   //입력양식 중 name태그 사용하는ㄱ것
      let $el = $(el);
      let name = $el.attr("name");
      let initValue = $el.data("initValue");
      $el.val(initValue);
   }).on("change",function(event){
      $(this.form).submit();
   });
   
   $(document).on("click", ".control-a",function(){
      let $el = $(this);      // this = event.target
      $(calForm).find('[name=year]').val($el.data("year"));
      $(calForm).find('[name=month]').val($el.data("month"));
      $(calForm).submit();
      /*calForm.year.value = el.dataset.year;         위와 동일
      calForm.month.value = el.dataset.month;
      calForm.requestSubmit();*/
   });
   
   let $calForm = $(calForm).on("submit",function(event){
      event.preventDefault();
//      $(this) === $calForm
      let url = this.action;
      let method = $(this).attr("method");
      let queryString = $(this).serialize();
      /*let formData = new FormData(calForm);      // 이벤트의 타겟 혹은 칼폼 넘겨줌
      let urlSearchParams = new URLSearchParams(formData);*/      // 두줄 queryString으로 줄임
      let settings = {
         url : url,      // 프로퍼티이름 : 값을 설정하고 있는 이름
         method : method,
         data : queryString,
         dataType : "html",
         success : function(resp){
            $(calArea).html(resp);
         }, error : function(jqXHR, errStatus, textError){
            console.log(jqXHR, errStatus, textError);
         }, complete : function(){
            console.log("비동기 요청 처리 최종 완료");
         }
      }
      $.ajax(settings);
   
   }).submit();
});