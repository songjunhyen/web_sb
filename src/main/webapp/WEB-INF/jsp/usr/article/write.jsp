<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../../common/head2.jsp"%>
</head>
<body>
	<form action="/usr/article/doWrite" method="post">
		<label for="title">Title:</label><br> 
		<input type="text" id="title" name="title" placeholder="제목을 입력하세요"><br>
		<label for="body">Body:</label><br>
		<textarea id="body" name="body" placeholder="내용을 입력하세요"></textarea>
		<br><br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>