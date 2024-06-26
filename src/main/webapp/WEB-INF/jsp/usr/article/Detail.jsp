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
<title>${foundArticle.title} 게시물</title>
<%@ include file="../../common/head2.jsp"%>
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
    <button onclick="history.back();">뒤로가기</button>
	<form action="/usr/article/Modify" method="get">
	    <input type="hidden" name="id" value="${foundArticle.id}">
	    <button type="submit">수정하기</button>
	</form>
	<a href="doDelete?id=${foundArticle.id }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
    <%@ include file="../../common/foot.jsp"%>
</body>
</html>