<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.example.demo.vo.Article"%>

<%
Article foundArticle = (Article) request.getAttribute("foundArticle");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
    <h1>게시글 확인</h1>
    <section>
        <div>
            <div class="detail-table">
                <div>
                    <span class="bold">제목 : </span>${foundArticle.title}
                </div>
                <div>
                    <span class="bold">작성자 : </span>${foundArticle.writer}
                </div>
                <div>
                    <span class="bold">작성일 : </span>${fn:substring(foundArticle.regDate, 0, 10)}
                </div>
                <div>
                    <span class="bold">조회수 : </span>${foundArticle.viewcount}
                </div>
                <div>
                    <span class="bold">내용 : </span>${foundArticle.body}
                </div>
            </div>
        </div>
    </section>
	<form action="/usr/article/modify" method="POST">
        <input type="hidden" name="id" value="${foundArticle.id}">
		제목: <input type="text" name="title"><br> 
		내용: <input type="text" name="body"><br>
		<input type="submit" value="글 수정하기">
	</form>
</body>
</html>