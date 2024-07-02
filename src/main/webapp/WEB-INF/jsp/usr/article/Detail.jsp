<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.example.demo.vo.Article"%>

<%
Article foundArticle = (Article) request.getAttribute("foundArticle");
%>
<%@ include file="../../common/head.jsp" %>

	<script>
		$(document).ready(function(){
			getLikePoint();
			
			$('#likePointBtn').click(async function(){
				
				let likePointBtn = $('#likePointBtn').hasClass('btn-active');
				try{
					await $.ajax({
						url : '/usr/article/likePoint/doLikePoint',
						type : 'GET',
						data: {
						    relTypeCode: 'article',
						    relId: ${foundArticle.id},
						    likePointBtn: likePointBtn
						},
					})
					
					let totalCnt = await getLikePoint();
					
					$('#likePointCnt').html(totalCnt.data + ' 개');
				} catch (error) {
					console.log('Error : ', error);
				}
			})
		})
		
		const getLikePoint = async function(){
			return $.ajax({
				url : '/usr/article/likePoint/getLikePoint',
				type : 'GET',
				data : {
					relTypeCode : 'article',
					relId : ${foundArticle.id }
				},
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$('#likePointBtn').addClass('btn-active');
					} else {
						$('#likePointBtn').removeClass('btn-active');
					}
				},
				error : function(xhr, status, error) {
					console.log(error);
				}
			})
		}
		
		function fetchReplyList() {
		    $.ajax({
		        url: '/usr/article/Reply/getReply',
		        type: 'GET',
		        data: {
		            relTypeCode: 'article',
		            relId: ${foundArticle.id}
		        },
		        success: function(data) {
		            $('#replyList').html(data);
		        },
		        error: function(xhr, status, error) {
		            console.error('Error: ', error);
		        }
		    });
		}

		$(document).ready(function() {
		    fetchReplyList(); // 페이지 로딩 시 댓글 목록을 불러옴
		});
		
		function submitReply() {
		    let relTypeCode = $('#relTypeCode').val();
		    let relId = $('#relId').val();
		    let body = $('#body').val();

		    $.ajax({
		        url: '/usr/article/Reply/WriteReply',
		        type: 'POST',
		        data: {
		            relTypeCode: relTypeCode,
		            relId: relId,
		            body: body
		        },
		        success: function(data) {
		            fetchReplyList(); // 성공적으로 댓글을 작성한 후 목록 갱신
		            $('#body').val(''); // 입력 폼 초기화
		        },
		        error: function(xhr, status, error) {
		            console.error('Error: ', error);
		        }
		    });
		}
		
	</script>
	<section class="mt-8 text-lg">
		<div class="container mx-auto px-3">
			<div class="table-box-type">
				<table class="table table-lg">
					<tr>
						<th>번호</th>
						<td>${foundArticle.id }</td>
					</tr>
					<tr>
						<th>작성일</th>
						<td>${foundArticle.updateDate }</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td>${foundArticle.viewcount }</td>
					</tr>
					<tr>
						<th>추천수</th>
						<td>
						<button id="likePointBtn" class="btn btn-sm btn-outline"> 추천</button> 
							&nbsp;&nbsp; 
						<span id="likePointCnt">${foundArticle.likePoint } 개</span>
					</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${foundArticle.writer}</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${foundArticle.title }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>${foundArticle.body }</td>
					</tr>
				</table>					
			</div>
			<div id="replyList">
			<form id="replyForm">
			    <input type="hidden" id="relTypeCode" name="relTypeCode" value="article">
			    <input type="hidden" id="relId" name="relId" value="${foundArticle.id}">
			    <textarea id="body" name="body" rows="4" cols="50"></textarea>
			    <button type="button" onclick="submitReply()">댓글 등록</button>
			</form>
			
			</div>
			
			<div class="mt-3 text-sm">
				<button class="btn btn-active btn-sm" onclick="history.back();">뒤로가기</button>
					<a class="btn btn-active btn-sm" href="Modify?id=${foundArticle.id }">수정</a>
					<a class="btn btn-active btn-sm" href="doDelete?id=${foundArticle.id }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>

			</div>
		</div>
	</section>
	
<%@ include file="../../common/foot.jsp" %>