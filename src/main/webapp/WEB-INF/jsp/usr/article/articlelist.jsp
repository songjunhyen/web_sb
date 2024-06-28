<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.List"%>
<%@ page import="com.example.demo.vo.Article"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시물 목록</title>
    <%@ include file="../../common/head2.jsp" %>
</head>
<body>
    <c:choose>
        <c:when test="${boardid == '1'}">
            <h1>공지사항</h1>
        </c:when>
        <c:when test="${boardid == '2'}">
            <h1>자유게시판</h1>
        </c:when>
       <c:when test="${boardid == '3'}">
            <h1>기타</h1>
        </c:when>
        <c:otherwise>
            <h1>전체 게시글</h1>
        </c:otherwise>
    </c:choose>
    <section>
        <div>
            <div class="table-box-type">
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="article" items="${articles}">
                            <tr>
                            	<td>${article.id}</td>
                                <td><a href="detail?id=${article.id}">${article.title}</a></td>
                                <td>${article.writer}</td>
                                <td>${fn:substring(article.regDate, 0, 10)}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>        
    </section>

	<%-- 페이징 버튼 --%>
	<div>
	    <%-- 이전 페이지 --%>
	    <c:if test="${currentPage > 1}">
	        <a href="?boardid=${boardid}&keyword=${keyword}&page=${currentPage - 1}">이전</a>
	    </c:if>
	
	    <%-- 페이지 번호 --%>
	    <c:forEach begin="1" end="${totalPages}" varStatus="loop">
	        <c:choose>
	            <c:when test="${loop.index == currentPage}">
	                <strong>${loop.index}</strong>
	            </c:when>
	            <c:otherwise>
	                <a href="?boardid=${boardid}&keyword=${keyword}&page=${loop.index}">${loop.index}</a>
	            </c:otherwise>
	        </c:choose>
	    </c:forEach>
	
	    <%-- 다음 페이지 --%>
	    <c:if test="${currentPage < totalPages}">
	        <a href="?boardid=${boardid}&keyword=${keyword}&page=${currentPage + 1}">다음</a>
	    </c:if>
	</div>

    </div>
    <button onclick="history.back();">뒤로가기</button>
    <br>
    <a href="/usr/article/Write">글쓰기</a>
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
    
    <c:if test="${not empty errorMessage}">
        <script>
            alert("${errorMessage}");
        </script>
    </c:if>

    <%@ include file="../../common/foot.jsp" %>
</body>
</html>