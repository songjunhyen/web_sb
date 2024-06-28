<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../../common/head2.jsp"%>
</head>
<body>
    <form action="/usr/article/doWrite" method="post">
        <label for="title">Title:</label><br>
        <input type="text" id="title" name="title" placeholder="제목을 입력하세요"><br>

        <label for="body">Body:</label><br>
        <textarea id="body" name="body" placeholder="내용을 입력하세요"></textarea><br>

        <label for="board">게시판 선택:</label><br>
        <select id="board" name="board" onchange="setBoardId()">
            <option value="">게시판을 선택하세요</option>
            <option value="1">공지사항</option>
            <option value="2">자유게시판</option>
            <option value="3">기타</option>
        </select><br><br>

        <input type="hidden" id="boardId" name="boardid" value="">

        <button type="submit">Submit</button>
    </form>
        <button onclick="history.back();">뒤로가기</button>

    <script>
        function setBoardId() {
            var boardSelect = document.getElementById("board");
            var boardId = boardSelect.options[boardSelect.selectedIndex].value;
            document.getElementById("boardId").value = boardId;
        }
    </script>
</body>
</html>