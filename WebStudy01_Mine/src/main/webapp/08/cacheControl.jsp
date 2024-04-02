<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>캐시 제어</h4>
<h4>캐싱하지 않는 방법</h4>
<pre>
	Cache : 자원이 네트워크를 통해 전송되는 동안 발생하는 부하와 latency time을 줄이기 위한 저장 데이터 형태
			 (주로 정적자원만 캐싱하지만, 간혹 동적자원도 캐싱하는데 주기적으로 갱신돼야하는 데이터를 캐싱하면 안될때 문제가 됨)
	: Paragma(http 1.0), Cache-Control(http 1.1), Expires(version 무관, Date(long)) 응답 헤더로 캐시 제어
	response.setHeader(name, value), setIntHeader(name, int value), setDateHeader(name, long value)
	response.setHeader(name, value), addIntHeader(name, int value), setDateHeader(name, long value)
	<%
		response.setHeader("Paragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0); //epoch time
	%>
</pre>
</body>
</html>