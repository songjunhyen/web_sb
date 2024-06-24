<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script>
	// 에러 메시지가 있을 때 경고창을 띄우는 함수
	function showError(errorMessage) {
		alert(errorMessage);
	}
</script>
</head>
<body>
	<a href="/">홈으로</a>
	<br>
	<form action="/usr/user/Login" method="post">
		<label for="userid">아이디:</label><br> <input type="text"
			id="userid" name="userid" placeholder="아이디를 입력해주세요"><br>
		<br> <label for="userpw">비밀번호:</label><br> <input
			type="password" id="userpw" name="userpw" placeholder="비밀번호를 입력해주세요"><br>
		<br>

		<c:if test="${not empty errorMessage}">
			<script>
				// 에러 메시지 출력
				showError("${errorMessage}");
			</script>
		</c:if>

		<input type="submit" value="로그인">
	</form>
</body>
</html>