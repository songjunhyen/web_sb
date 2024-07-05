<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
    <%@ include file="../../common/head2.jsp"%>

<!-- 
 <script>
 에 유저정보를 ajax로 요청해서 받고 비교해서 표시하도록 아이디가 일치하지않습니다 이런식으로
 비었는지도 체크
 </script>    
-->


<style type="text/css">
.update{
  text-align: center;
  width:100%:
}
.update form{    
  width:100%:
}
.update form label{
  width:100%:
}
.update form input{
  text-align: center; 
  width:100%:
}
</style>
</head>
<body>
<div class="update">
	<form action="/usr/user/Update" method="post">
		<label for="userid">사용중인 User ID:</label><br> 
		<input type="text" id="userid" name="userid" placeholder="사용자 아이디"><br>
		<label for="userid">사용중인 User PW:</label><br> 
		<input type="password" id="userpw" name="userpw" placeholder="사용자 비번"><br>
		<label for="userpw">Password:</label><br>
		<input type="password" id="pw" name="pw" placeholder="비밀번호"><br>
		<label for="nickname">Nickname:</label><br>
		<input type="text" id="nickname" name="nickname" placeholder="별명"><br>
		<button type="submit">회원정보 변경하기</button>
	</form>
</div>
</body>
</html>