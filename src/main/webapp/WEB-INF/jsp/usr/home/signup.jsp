<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
    <%@ include file="../../common/head.jsp"%>
    <style>
        .error-message {
            color: red;
            display: none;
        }
    </style>
  <script>
        $(document).ready(function() {
            // 아이디 입력 필드에서 포커스를 잃었을 때 유효성 검사
            $("#userid").blur(function() {
                checkEmptyInput("userid", "useridError", "아이디를 입력해주세요.");
            });

            // 비밀번호 입력 필드에서 포커스를 잃었을 때 유효성 검사
            $("#userpw").blur(function() {
                checkEmptyInput("userpw", "pwError", "비밀번호를 입력해주세요.");
            });

            // 비밀번호 확인 입력 필드에서 포커스를 잃었을 때 비밀번호 일치 여부 확인
            $("#userpw2").blur(function() {
                checkPasswordMatch();
            });

            // 닉네임 입력 필드에서 포커스를 잃었을 때 유효성 검사
            $("#nickname").blur(function() {
                checkEmptyInput("nickname", "nicknameError", "닉네임을 입력해주세요.");
            });

            // 입력 필드 오류 메시지 표시 함수
            function showError(errorId, errorMessage) {
                $("#" + errorId).html(errorMessage);
                $("#" + errorId).show();
            }

            // 입력 필드 오류 메시지 숨기는 함수
            function hideError(errorId) {
                $("#" + errorId).html("");
                $("#" + errorId).hide();
            }

            // 아이디, 비밀번호, 닉네임이 비어 있는지 확인하는 함수
            function checkEmptyInput(fieldId, errorMessageId, errorMessage) {
                var fieldValue = $("#" + fieldId).val().trim();
                var errorMessageElement = $("#" + errorMessageId);

                if (fieldValue === "") {
                    errorMessageElement.html(errorMessage);
                    errorMessageElement.show();
                    return false; // 입력값이 비어있음
                } else {
                    errorMessageElement.html("");
                    errorMessageElement.hide();
                    return true; // 입력값이 있음
                }
            }

            // 비밀번호와 비밀번호 확인이 일치하는지 확인하는 함수
            function checkPasswordMatch() {
                var userpw = $("#userpw").val().trim();
                var userpw2 = $("#userpw2").val().trim();
                var userpw2Error = $("#pw2Error");

                if (userpw !== userpw2) {
                    userpw2Error.html("비밀번호가 일치하지 않습니다.");
                    userpw2Error.show();
                    return false;
                } else {
                    userpw2Error.html("");
                    userpw2Error.hide();
                    return true;
                }
            }
        });
    </script>
</head>
<body>
<form id="signupForm" action="/usr/user/Signup" method="post">
    <label for="userid">아이디:</label><br>
    <input type="text" id="userid" name="userid" placeholder="아이디를 입력해주세요"><br>
    <div id="useridError" class="error-message"></div><br>

    <label for="userpw">비밀번호:</label><br>
    <input type="password" id="userpw" name="userpw" placeholder="비밀번호를 입력해주세요"><br>
    <div id="pwError" class="error-message"></div><br>

    <label for="userpw2">비밀번호 확인:</label><br>
    <input type="password" id="userpw2" name="userpw2" placeholder="비밀번호를 입력해주세요"><br>
    <div id="pw2Error" class="error-message"></div><br>

    <label for="nickname">닉네임:</label><br>
    <input type="text" id="nickname" name="nickname" placeholder="닉네임을 입력해주세요"><br>
    <div id="nicknameError" class="error-message"></div><br>

    <input type="submit" value="회원가입">
</form>

<c:if test="${not empty errorMessage}">
    <script>
        alert("${errorMessage}");
    </script>
</c:if>
</body>
</html>