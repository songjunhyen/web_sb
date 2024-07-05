<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
    <%@ include file="../../common/head2.jsp"%>
</head>



<!-- 
 <script>
 에 ajax로 아이디, 비밀번호 처럼 넣은거 비었거나 조건에 안맞으면 그냥 아래 글자로
 
아이디: 5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.
비밀번호: 8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.
생년월일: 생년월일은 8자리 숫자로 입력해 주세요. 
이런거 비동기적으로 체크하도록	

이런식으로 나오게 수정하기

 </script>    
-->

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