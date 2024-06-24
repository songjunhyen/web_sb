<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>메인 페이지</title>
<script>
	// 에러 메시지가 있을 때 경고창을 띄우는 함수
	function showError(errorMessage) {
		alert(errorMessage);
	}
</script>

<link rel="stylesheet" type="text/css" href="/resource/common.css">
</head>
<body>
	<h1>메인 페이지</h1>

	<a href="/usr/home/login">로그인</a>

	<br>

	<a href="/usr/home/signup">회원가입</a>

	<br>

	<c:if test="${not empty errorMessage}">
		<script>
			// 에러 메시지 출력
			showError("${errorMessage}");
		</script>
	</c:if>

</body>
</html>