<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle }</title>

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
.page {
  border: 2px solid black;
  width: 100%;
  display: flex;
  justify-content: space-between;
  padding: 10px;
}

.page > div {
  height: 0%;
}

.page > ul {
  display: flex;
  list-style-type: none; /* 리스트 스타일 제거 */
  padding: 0; /* 패딩 제거 */
}

.page > ul > li {
  margin-left: 10px; /* 각 리스트 항목 사이 여백 설정 */
}

.page > ul > li > a {
  text-decoration: none; /* 링크 밑줄 제거 */
  padding: 10px; /* 링크 안의 여백 설정 */
}

.page > div > a {
  text-decoration: none; /* 링크 밑줄 제거 */
}
</style>
</head>
<body>
	<div class="page">
		<a href="/" class="logo"><span>로고</span></a>
		<ul>
			<li><a href="/">HOME</a></li>
		</ul>
	</div>
