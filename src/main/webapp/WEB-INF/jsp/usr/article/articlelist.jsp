<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.List"%>
<%@ page import="com.example.demo.vo.Article"%>
<%@ page import="util.Util"%>


<!-- 
 <script>
 에 ajax로
 
 페이지네이션:
다음 페이지나 이전 페이지를 비동기적으로 로드하여 사용자가 스무스하게 페이지를 탐색할 수 있도록 합니다.
페이지 번호를 클릭할 때마다 해당 페이지의 내용을 AJAX를 이용하여 동적으로 업데이트합니다. 
loadPage 함수는 클릭한 링크의 href를 가져와서 해당 URL로 AJAX 요청을 보내고, 
서버에서 받은 HTML 데이터로 페이지를 업데이트합니다. 
이를 통해 사용자가 페이징 버튼을 클릭할 때 페이지가 새로 고치지 않고도 새로운 페이지의 데이터를 가져올 수 있습니다.
 </script>    
-->

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
                            <th>조회수</th>
                            <th>작성일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="article" items="${articles}">
                            <tr>
                            	<td>${article.id}</td>
                                <td><a href="detail?id=${article.id}">${article.title}</a></td>
                                <td>${article.writer}</td>
                                <td>${article.viewcount}</td>
                                <td>${Util.formatDate(article.regDate)}</td>
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