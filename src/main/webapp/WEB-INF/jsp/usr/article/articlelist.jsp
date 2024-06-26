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
<%@ include file="../../common/head2.jsp"%>
</head>
<body>
	<h1>아티클 목록</h1>

	<%
	Object obj = request.getAttribute("articles");
	if (obj instanceof List<?>) {
		List<Article> articles = (List<Article>) obj;
	%>

	<section>
		<div>
			<div class="table-box-type">
				<table>
					<thead>
						<tr>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles}">
							<tr>
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

	<%
	}
	%>
	<br>
	<a href="/usr/article/Write">글쓰기</a>
	<br>
	<form action="/usr/article/Search" method="GET">
		키워드: <input type="text" name="keyword"><br> <input
			type="submit" value="검색하기">
	</form>
		
    <c:if test="${not empty errorMessage}">
        <script>
            alert("${errorMessage}");
        </script>
    </c:if>
	
	<%@ include file="../../common/foot.jsp"%>
</body>
</html>