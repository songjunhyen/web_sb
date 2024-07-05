<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.example.demo.vo.Article"%>

<%@ include file="../../common/head2.jsp"%>
<%@ include file="../../common/toastUiEditorLib.jsp" %>

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
		<button onclick="history.back();">뒤로가기</button>
	</section>
	<section class="mt-8 text-lg">
		<div class="container mx-auto px-3">
			<div class="w-9/12 mx-auto">
				<form action="/usr/article/modify" method="POST"
					onsubmit="return submitForm(this)">
					<input type="hidden" name="id" value="${foundArticle.id}">
					<input type="hidden" name="body" />
					<div class="w-9/12 mx-auto">

						<table class="table table-lg">

							<tr>
								<th>게시판</th>
							</tr>

							<tr>
								<th>제목</th>
								<td><input class="input input-bordered w-full" type="text"
									name="title" /></td>
							</tr>
							<tr>
								<th>내용</th>
								<td><div class="toast-ui-editor"></div></td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="btns flex justify-center">
										<button class="btn btn-active btn-wide">작성</button>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<button onclick="history.back();">뒤로가기</button>
				</form>
				</div>				
			</div>
	</section>
</body>
</html>