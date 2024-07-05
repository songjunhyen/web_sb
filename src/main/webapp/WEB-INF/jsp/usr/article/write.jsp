<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
<%@ include file="../../common/head2.jsp"%>
<%@ include file="../../common/toastUiEditorLib.jsp"%>

</head>
<section class="mt-8 text-lg">
	<div class="container mx-auto px-3">
		<form action="/usr/article/doWrite" method="post"
			onsubmit="return submitForm(this)">
			<input type="hidden" name="body" />
			<div class="w-9/12 mx-auto">
				<label for="board">게시판 선택:</label> <br>
				 <select id="board"	name="board" onchange="setBoardId()">
					<option value="">게시판을 선택하세요</option>
					<option value="1">공지사항</option>
					<option value="2">자유게시판</option>
					<option value="3">기타</option>
				</select> <br> <br> <input type="hidden" id="boardId"
					name="boardid" value="">
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
</section>
<script>
	function setBoardId() {
		var boardSelect = document.getElementById("board");
		var boardId = boardSelect.options[boardSelect.selectedIndex].value;
		document.getElementById("boardId").value = boardId;
	}
</script>
</body>
</html>

