<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="fileArea"></div>
<form method="post" enctype="multipart/form-data">
	<input type="file" name="uploadFile" multiple id="fileForm" class="form-control">
	<button type="submit">업로드</button>
</form>
	
<script type="text/javascript" src="<c:url value='/resources/js/app/mission/fileForm.js'/>"></script>
