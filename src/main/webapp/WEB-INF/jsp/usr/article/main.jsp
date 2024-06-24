<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>

<script>
    function showError(errorMessage) {
        alert(errorMessage);
    }
</script>

</head>
<body>
	<h1>메인 페이지</h1>
	<form action="/usr/article/doWrite" method="post">
		<label for="title">Title:</label><br> 
		<input type="text" id="title" name="title" placeholder="제목을 입력하세요"><br>
		<label for="body">Body:</label><br>
		<textarea id="body" name="body" placeholder="내용을 입력하세요"></textarea>
		<br><br>
		<input type="submit" value="Submit">
	</form>
	<a href="/usr/article/showList">글 목록 보기</a>
	<br><br>
	<form action="/usr/article/detail" method="GET">
		글 번호: <input type="text" name="id"><br>
		<input type="submit" value="글 상세보기">
	</form>
	<br>
	<form action="/usr/article/Search" method="GET">
		키워드: <input type="text" name="keyword"><br>
		<input type="submit" value="검색하기">
	</form>
	<form action="/usr/article/Modify" method="POST">
		글 번호: <input type="text" name="id"><br> 
		제목: <input type="text" name="title"><br> 
		내용: <input type="text" name="body"><br>
		<input type="submit" value="글 수정하기">
	</form>
	<br>
	<form action="/usr/user/Logout" method="post">
	    <button type="submit">로그아웃</button>
	</form>

	<c:if test="${not empty errorMessage}">
        <script>
            showError("${errorMessage}");
        </script>
    </c:if>

	<br>
	<form action="/usr/user/Update" method="post">
		<label for="userid">User ID:</label><br> 
		<input type="text" id="userid" name="userid" placeholder="사용자 아이디"><br>
		<label for="userpw">Password:</label><br>
		<input type="password" id="userpw" name="userpw" placeholder="비밀번호"><br>
		<label for="nickname">Nickname:</label><br>
		<input type="text" id="nickname" name="nickname" placeholder="별명"><br>
		<input type="submit" value="회원정보 변경하기">
	</form>
	<br>
	<form action="/usr/user/delete" method="post">
		<label for="userid_delete">User ID:</label><br> 
		<input type="text" id="userid_delete" name="userid" placeholder="사용자 아이디"><br>
		<label for="userpw_delete">Password:</label><br>
		<input type="password" id="userpw_delete" name="userpw" placeholder="비밀번호"><br>
		<input type="submit" value="회원정보 삭제하기">
	</form>
	<br>
	
</body>
</html>