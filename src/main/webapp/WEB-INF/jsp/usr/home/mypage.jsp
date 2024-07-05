<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<%@ page import="com.example.demo.vo.User"%>    
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${user.id}님의 정보</title>
    <%@ include file="../../common/head2.jsp" %>
</head>
<body>
	<section class="mt-8 text-lg">
		<div class="container mx-auto px-3">
			<div class="table-box-type">
				<table class="table table-lg">
					<tr>
						<th>번호</th>
						<td>${user.id}</td>
					</tr>
					<tr>
						<th>가입일</th>
						<td>${user.regDate}</td>
					</tr>
					<tr>
						<th>아이디</th>
						<td>${user.userid}</td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td>${user.nickname}</td>
					</tr>
				</table>
			</div>
		</div>
	</section>

	<br>
	<button onclick="location.href='/usr/user/updated'"
		class="btn btn-sm btn-link">회원정보 수정</button>
	<br>
	<button onclick="location.href='/usr/user/delete'"
		class="btn btn-sm btn-link">회원정보 삭제</button>
	<br>
</body>
</html>