<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/responseDesc.jsp</title>
</head>
<body>
	<h4>서버에서 클라이언트로 전송되는 응답 형태 : HttpServletResponse</h4>
	<pre>
		1. Response Line ; status code
			status code : 요청 처리의 성공/실패 여부를 표현할 수 있는 세자리 숫자 (100~500번대 숫자)
			
				1) 100~ : Http 프로토콜(Connect-Less = 연결지양, State-Less = 상태를 남기지 않는다)
							ING..., WebSocket 프로토콜에서 사용되고, 주로 양방향 실시간 통신이나 push 메세지 서비스에 활용됨.
				2) 200~ : OK(<%=HttpServletResponse.SC_OK %>) (Request Headers의 Accept(타입)와 Response Headers의 Content-Type이 일치해야 진짜 성공된 응답처리임)
				3) 300~ : response body가 없기때문에 클라이언트가 응답을 받은 후 추가 액션을 하게 됨
					304 : Not Modified<%=HttpServletResponse.SC_NOT_MODIFIED %> 그 추가 액션으로 캐시영역을 검색함
						정적자원에 대한 요청에 대해선, 클라이언트는 서버를 거치치 않고 클라이언트 자신의 캐시저장소에 저장해 놓은 자원을 응답으로 준다.
						그런데, 클라이언트 자신이 캐시에 갖고있던 자원이 진짜 수정된적이 없는지 서버에 물어보는데 서버는 진짜 수정된게 아닐때 304코드를 줌
						
					302/307 : Moved, 자원의 새로운 위치를 대상으로 새로운 요청을 전송함(send redirect할때 쓰는 코드)
							
				
				4) 400~ : client side error = 실패원인이 클라이언트에게 있을때 (클라이언트쪽 오류는 500번대보다 구체적으로 표시함)
					400 :<%=HttpServletResponse.SC_BAD_REQUEST %> : 필수 파라미터 누락, 요청 데이터 타입 문제, 요청 데이터 길이가 너무 길때(서버가 요청 검증시 의도적으로 응답으로 내보냄)
					401/403 : <%=HttpServletResponse.SC_UNAUTHORIZED %> <%=HttpServletResponse.SC_FORBIDDEN %> 서버자원을 클라이언트에게 접근제어 하거나 보안처리 할때 사용하는 코드
								인증(검증)을 거친 후 인가(검증한 대상에 권한 확인)가 되면 '보안처리' 됐다고 함
					404 : <%=HttpServletResponse.SC_NOT_FOUND %> URL주소가 잘못 됐을때
					405 : <%=HttpServletResponse.SC_METHOD_NOT_ALLOWED %> 서버단의 서블릿에서 해당 do계열의 콜백함수들을 override하지 않았을때
					406 : <%=HttpServletResponse.SC_NOT_ACCEPTABLE %> 
							클라이언트가 request Accept header에 text/xml등을 응답으로 요청했는데, 서버는 xml을 만들어낼 마샬러가 없어서, 헤더를 응답으로 못내보낼때
					416 : <%=HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE%> 
							클라이언트가 서버로 요청을 보냈는데 읽을 수 없어서 request Content-Type 헤더를 처리할 수 없음 (= 언마샬을 할 수 없을때)
							
				5)  500~ : server side error (서버쪽 에러는 500코드 하나로 씀, 400번대보다 덜 디테일한 이유는 보안상의 이유. 503은 server is not available 서버가 감당할 수 있는 트래픽 초과)
							
				
		2. Response Header (name/value)
			Content-*
			Content-Length : response body의 길이
			Content-Type : response body content's MIME
			
			1) Content-Type : response content MIME
			2) Cache-Control : 캐시 제어
			3) Refresh : auto-request
			4) Location : 자원이 어딘가로 이동됐을때, 새로운 위치를 알려주는 헤더(sendRedirect)
		3. Response Body(=Content Body, Message Body) : 응답 컨텐츠 영역
	</pre>
</body>
</html>