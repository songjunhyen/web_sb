<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>메인 페이지</title>        
    <link rel="stylesheet" type="text/css" href="/resource/common.css">
    <%@ include file="../../common/head.jsp"%>
</head>
<body>
    <h1>메인 페이지</h1>
    <!-- 로그인 버튼 -->
    <button onclick="location.href='/usr/home/login'" class="btn btn-sm btn-primary">로그인</button>
    
    <!-- 회원가입 버튼 -->
    <button onclick="location.href='/usr/home/signup'" class="btn btn-sm btn-primary">회원가입</button>
    
    <!-- JavaScript로 에러 메시지 출력 -->
    <c:if test="${not empty errorMessage}">
        <script>
            alert("${errorMessage}");
        </script>
    </c:if>

</body>
</html>