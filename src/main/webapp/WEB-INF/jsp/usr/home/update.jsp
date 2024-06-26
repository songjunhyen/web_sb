<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>
	<form action="/usr/user/Update" method="post">
		<label for="userid">사용중인 User ID:</label><br> 
		<input type="text" id="userid" name="userid" placeholder="사용자 아이디"><br>
		<label for="userid">사용중인 User PW:</label><br> 
		<input type="password" id="userpw" name="userpw" placeholder="사용자 비번"><br>
		<label for="userpw">Password:</label><br>
		<input type="password" id="pw" name="pw" placeholder="비밀번호"><br>
		<label for="nickname">Nickname:</label><br>
		<input type="text" id="nickname" name="nickname" placeholder="별명"><br>
		<input type="submit" value="회원정보 변경하기">
	</form>
</body>
</html>