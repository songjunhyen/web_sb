<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.example.demo.vo.Article"%>
<%@ page import="com.example.demo.vo.Reply"%>
<%@ page import="java.util.List"%>


<%
Article foundArticle = (Article) request.getAttribute("foundArticle");
%>


<%@ include file="../../common/head2.jsp" %>

<script>
    $(document).ready(function(){
        getLikePoint();

        $('#likePointBtn').click(async function(){
            let likePointBtn = $('#likePointBtn').hasClass('btn-active');
            try {
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
		<script>
		    const replyForm_onSubmit = function(form){
		        form.body.value = form.body.value.trim();
		
		        if (form.body.value.length == 0) {
		            alert('비어있는 댓글은 작성할 수 없습니다');
		            form.body.focus();
		            return;
		        }
		        
		        // Convert foundArticle.id to integer
		        let relId = parseInt('${foundArticle.id}');
		
		        // Set the value to the hidden input
		        form.querySelector('input[name="relId"]').value = relId;
		
		        form.submit();
		    }
		</script>
		<section class="my-5 text-base">
		    <div class="container mx-auto px-3">
		        <div class="text-lg">댓글</div>
		       
		        <c:forEach var="reply" items="${replies}">
				    <div class="py-2 border-bottom-line pl-16">
				    	<div class="text-m my-1 ml-2">${reply.writer}</div>
				        <div class="text-m my-1 ml-2">${reply.getForPrintBody()}</div>
				        <div class="text-xs text-gray-400">${reply.updateDate}</div>
				        
				<form action="/usr/article/Reply/DeleteReply" method="post" onsubmit="replyForm_onSubmit(this); return false;">
				    <input type="hidden" name="relTypeCode" value="article"/>
				    <input type="hidden" name="replyId" value="${reply.id}"/>
				    <div class="mt-4 reply-border p-4">
				        <div class="flex justify-end">
				            <!-- 삭제 버튼을 작성자와 현재 사용자의 ID를 비교하여 제어 -->
				            <c:if test="${not reply.writer.equals(userId)}">
				                <button class="btn btn-active btn-sm" style="display:none;">삭제</button>
				            </c:if>
				            <c:if test="${reply.writer.equals(userId)}">
				                <button class="btn btn-active btn-sm">삭제</button>
				            </c:if>
				        </div>
				    </div>
				</form>
				
				    </div>
				</c:forEach>      
		        <form action="/usr/article/Reply/WriteReply" method="post" onsubmit="replyForm_onSubmit(this); return false;">
		            <input type="hidden" name="relTypeCode" value="article"/>
		            <input type="hidden" name="relId" value="${foundArticle.id}"/>
		            <div class="mt-4 reply-border p-4">
		                <div class="mb-2"><span>닉네임</span></div>
		                <textarea class="textarea textarea-bordered textarea-lg w-full" name="body" placeholder="댓글을 입력해보세요"></textarea>
		                <div class="flex justify-end"><button class="btn btn-active btn-sm">작성</button></div>
		            </div>
		        </form>

		    </div>  
		</section>

        <div class="mt-3 text-sm">
            <button class="btn btn-active btn-sm" onclick="history.back();">뒤로가기</button>
            <a class="btn btn-active btn-sm" href="Modify?id=${foundArticle.id }">수정</a>
            <a class="btn btn-active btn-sm" href="doDelete?id=${foundArticle.id }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
        </div>
    </div>
</section>

<%@ include file="../../common/foot.jsp" %>