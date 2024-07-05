<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle }</title>
<!-- 파비콘추가 -->
<link rel="shortcut icon" href="/resource/images/favicon.ico" />
<!-- 데이지 UI -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@4.12.8/dist/full.min.css" rel="stylesheet" type="text/css" />
<!-- 테일윈드 -->
<script src="https://cdn.tailwindcss.com"></script>
<!-- 폰트어썸 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"></script>
<!-- 제이쿼리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- 공통 css -->
<link rel="stylesheet" href="/resource/common.css" />
<style type="text/css">


.toast-ui-editor {
	background-color: white;
}

.page {
	border: 2px solid black;
	width: 100%;
	display: flex;
	justify-content: space-between;
	padding: 10px;
}

.page>div {
	height: 0%;
}

.page>ul {
	display: flex;
	list-style-type: none; /* 리스트 스타일 제거 */
	padding: 0; /* 패딩 제거 */
}

.page>ul>li {
	margin-left: 10px; /* 각 리스트 항목 사이 여백 설정 */
}

.page>ul>li>a {
	text-decoration: none; /* 링크 밑줄 제거 */
	padding: 10px; /* 링크 안의 여백 설정 */
}

button {
	border: 2px solid black;
	text-decoration: none; /* 링크 밑줄 제거 */
	padding: 10px; /* 링크 안의 여백 설정 */
}

.page>div>a {
	text-decoration: none; /* 링크 밑줄 제거 */
}
</style>
</head>
<body>
	<div class="page">
		<div>
			<a href="/usr/article/Main"><span>로고</span></a>
		</div>

		<ul>
			<li><a href="/usr/article/Main"><span>HOME</span></a></li>
			<li><a href="/usr/article/showList?boardid=0"><span>LIST</span></a></li>
			<li><a href="/usr/user/MyPage"><span>MY PAGE</span></a></li>
			<li>
				<form action="/usr/user/Logout" method="post">
					<button type="submit" class="btn btn-sm btn-danger">LOGOUT</button>
				</form>
			</li>
		</ul>
	</div>