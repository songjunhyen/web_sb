<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="util.Util" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
    <%@ include file="../../common/head2.jsp"%>
</head>
<body>
    <br><br>
    <h1>메인 페이지</h1>
    <br>
    <button onclick="location.href='/usr/article/Write'" class="btn btn-sm btn-primary">글쓰기</button>
    <br>
    <a href="/usr/article/showList?boardid=0">모든게시글 목록</a>
    <br>
    <a href="/usr/article/showList?boardid=1">공지사항 게시판 보기</a>
    <br>
    <a href="/usr/article/showList?boardid=2">자유 게시판 보기</a>
    <br>
    <a href="/usr/article/showList?boardid=3">기타 게시판 보기</a>
    <br>
    
	<form action="/usr/article/Search" method="GET">
	    <label for="boardid">게시판 선택:</label>
	    <select name="boardid" id="boardid">
	        <option value="0">모든 게시글</option>
	        <option value="1">공지사항</option>
	        <option value="2">자유 게시판</option>
	        <option value="3">기타 게시판</option>
	    </select>
	    <br>
	    키워드: <input type="text" name="keyword" value="${param.keyword}">
	    <br>
	    <input type="submit" value="검색하기">
	</form>
	
    <br>
    <a href="/usr/user/updated">회원정보 수정</a>
    <br>
    <a href="/usr/user/delete">회원정보 삭제</a>
    <br>
    
    <form action="/usr/user/Logout" method="post">
        <button type="submit">로그아웃</button>
    </form>
    
    <c:if test="${not empty errorMessage}">
        <script>
            alert("${errorMessage}");
        </script>
    </c:if>

</body>
</html>