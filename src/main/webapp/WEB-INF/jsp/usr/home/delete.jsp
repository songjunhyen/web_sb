<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 제거</title>
    <%@ include file="../../common/head2.jsp"%>

</head>
<body>
	<form action="/usr/user/Delete" method="post">
		<label for="userid_delete">User ID:</label><br> 
		<input type="text" id="userid_delete" name="userid" placeholder="사용자 아이디"><br>
		<label for="userpw_delete">Password:</label><br>
		<input type="password" id="userpw_delete" name="userpw" placeholder="비밀번호"><br>
		<input type="submit" value="회원정보 삭제하기">
	</form>

</body>
</html>