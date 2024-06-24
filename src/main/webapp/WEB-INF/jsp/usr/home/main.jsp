<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>메인 페이지</title>
</head>
<body>
    <h1>메인 페이지</h1>

    <form action="/usr/user/Login" method="post">
        <label for="userid">아이디:</label><br>
        <input type="text" id="userid" name="userid" placeholder="아이디를 입력해주세요"><br><br>
        
        <label for="userpw">비밀번호:</label><br>
        <input type="password" id="userpw" name="userpw" placeholder="비밀번호를 입력해주세요"><br><br>
        
        <input type="submit" value="로그인">
    </form>

    <br>

    <form action="/usr/user/Signup" method="post">
        <label for="userid">아이디:</label><br>
        <input type="text" id="userid" name="userid" placeholder="아이디를 입력해주세요"><br><br>
        
        <label for="userpw">비밀번호:</label><br>
        <input type="password" id="userpw" name="userpw" placeholder="비밀번호를 입력해주세요"><br><br>
        
        <label for="userpw2">비밀번호 확인:</label><br>
        <input type="password" id="userpw2" name="userpw2" placeholder="비밀번호를 입력해주세요"><br><br>
        
        <label for="nickname">닉네임:</label><br>
        <input type="text" id="nickname" name="nickname" placeholder="닉네임을 입력해주세요"><br><br>
        
        <input type="submit" value="회원가입">
    </form>

    <br>

    <a href="/usr/user/Logout">로그아웃</a>

</body>
</html>