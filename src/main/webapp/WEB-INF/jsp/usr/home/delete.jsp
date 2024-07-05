<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 삭제</title>
    <%@ include file="../../common/head2.jsp"%>
<!-- 
 <script>
 에 유저정보를 ajax로 요청해서 받고 비교해서 표시하도록 아이디가 일치하지않습니다 이런식으로
 </script>    
-->
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