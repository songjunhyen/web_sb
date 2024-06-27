<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
    <%@ include file="../../common/head2.jsp"%>
</head>
<body>
    <form action="/usr/user/Signup" method="post">
        <label for="userid">아이디:</label><br>
        <input type="text" id="userid" name="userid" placeholder="아이디를 입력해주세요"><br><br>
        
        <label for="userpw">비밀번호:</label><br>
        <input type="password" id="userpw" name="userpw" placeholder="비밀번호를 입력해주세요"><br><br>
        
        <label for="userpw2">비밀번호 확인:</label><br>
        <input type="password" id="userpw2" name="userpw2" placeholder="비밀번호를 입력해주세요"><br><br>
        
        <label for="nickname">닉네임:</label><br>
        <input type="text" id="nickname" name="nickname" placeholder="닉네임을 입력해주세요"><br><br>
        
        <c:if test="${not empty errorMessage}">
     	   <script>
       	     alert("${errorMessage}");
     	   </script>
   		</c:if>
        
        <input type="submit" value="회원가입">
    </form>

</body>
</html>