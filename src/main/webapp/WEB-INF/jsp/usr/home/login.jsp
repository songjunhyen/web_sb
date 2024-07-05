<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<!-- 데이지 UI -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@4.12.8/dist/full.min.css" rel="stylesheet" type="text/css" />
<!-- 테일윈드 -->
<script src="https://cdn.tailwindcss.com"></script>
<!-- 폰트어썸 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"></script>
<!-- 제이쿼리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- 공통 css -->
<link rel="stylesheet" href="/resource/common.css" />
    <%@ include file="../../common/head.jsp"%>



</head>
<body>
	<a href="/">Home</a>
	<br>
	<form action="/usr/user/Login" method="post">
		<label for="userid">아이디:</label><br> <input type="text"
			id="userid" name="userid" placeholder="아이디를 입력해주세요"><br>
		<br> <label for="userpw">비밀번호:</label><br> <input
			type="password" id="userpw" name="userpw" placeholder="비밀번호를 입력해주세요"><br>
		<br>

		<input type="submit" value="로그인">
	</form>
</body>
</html>

