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
<%@ include file="../../common/head.jsp"%>
</head>
<body>
	<h1>아티클 목록</h1>

	<%
	List<Article> articles = (List<Article>) request.getAttribute("articles");
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
								<td>${article.title}</td>
								<td>${article.writer}</td>
								<td>${fn:substring(article.regDate, 0, 10)}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	<%@ include file="../../common/foot.jsp"%>
</body>
</html>