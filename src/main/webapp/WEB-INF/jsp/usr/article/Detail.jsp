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
    $(document).ready(function() {
        // 초기 추천 수를 가져오는 함수
        async function getLikePoint() {
            try {
                let response = await $.ajax({
                    url: '/usr/article/likePoint/getLikePoint',
                    type: 'GET',
                    data: {
                        relTypeCode: 'article',
                        relId: '${foundArticle.id}'
                    },
                    dataType: 'json'
                });

                // 가져온 추천 수를 화면에 표시
                $('#likePointCnt').html(response + ' 개');

            } catch (error) {
                console.error('Error:', error);
            }
        }
        // 페이지 로드 시 초기 추천 수 가져오기 
        getLikePoint();

        // 추천 버튼 클릭 시
        $('#likePointBtn').click(async function() {	        	
            try {
                // AJAX 요청으로 추천 처리
                let isLiked = $('#likePointBtn').hasClass('btn-active'); 
                await $.ajax({
                    url: '/usr/article/likePoint/doLikePoint',
                    type: 'GET',
                    data: {            
                        relTypeCode: 'article',
                        relId: '${foundArticle.id}'
                    },
                    success: function(data) {
                        // 추천 처리 후 반환된 데이터에서 totalCount 업데이트
                        $('#likePointCnt').html(data + ' 개');
                    },
                    error: function(xhr, status, error) {
                        console.error('Error:', error);
                    }
                });
            } catch (error) {
                console.error('Error:', error);
            }
        });
    });
    </script>

<section class="mt-8 text-lg">
    <div class="container mx-auto px-3">
        <div class="table-box-type">
            <table class="table table-lg">
                <tr>
                    <th>번호</th>
                    <td>${foundArticle.id}</td>
                </tr>
                <tr>
                    <th>작성일</th>
                    <td>${foundArticle.updateDate}</td>
                </tr>
                <tr>
                    <th>조회수</th>
                    <td>${foundArticle.viewcount}</td>
                </tr>
                <tr>
                    <th>추천수</th>
                    <td>
                        <button id="likePointBtn" class="btn btn-sm btn-outline">추천</button>
                        &nbsp;&nbsp;
                        <span id="likePointCnt">${foundArticle.likePoint} 개</span>
                    </td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${foundArticle.writer}</td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td>${foundArticle.title}</td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>${foundArticle.body}</td>
                </tr>
            </table>
        </div>
    </div>
</section>

<section class="my-5 text-base">
    <div class="container mx-auto px-3">
        <div class="text-lg">댓글</div>
        <c:forEach var="reply" items="${replies}">
            <div class="py-2 border-bottom-line pl-16">
                <div class="text-m my-1 ml-2">${reply.writer}</div>
                <div class="text-m my-1 ml-2">${reply.getForPrintBody()}</div>
                <div class="text-xs text-gray-400">${reply.updateDate}</div>

                <c:choose>
                    <c:when test="${reply.writer eq userId}">
                        <button id="changebt" onclick="showModifyForm()">수정</button>

                        <div id="modifyForm" style="display: none;">
                            <form action="/usr/article/Reply/ModifyReply" method="post"
                                onsubmit="return replyForm_onSubmit(this);">
                                <input type="hidden" name="relTypeCode" value="article" />
                                <input type="hidden" name="relId" value="${foundArticle.id}" />
                                <input type="hidden" name="Id" value="${reply.id}" />
                                <textarea class="textarea textarea-bordered textarea-lg w-full"
                                    name="body" placeholder="수정 댓글을 입력해주세요"></textarea>
                                <div class="mt-4 reply-border p-4">
                                    <div class="flex justify-end">
                                        <button type="submit" class="btn btn-active btn-sm">수정하기</button>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <script>
                            function showModifyForm() {
                                document.getElementById('modifyForm').style.display = 'block';
                            }
                        </script>
                    </c:when>
                    <c:otherwise>
                        <!-- 다른 경우 처리 -->
                    </c:otherwise>
                </c:choose>

                <form action="/usr/article/Reply/DeleteReply" method="post"
                    onsubmit="return replyForm_onSubmit(this); return false;">
                    <input type="hidden" name="relTypeCode" value="article" />
                    <input type="hidden" name="relId" value="${foundArticle.id}" />
                    <input type="hidden" name="Id" value="${reply.id}" />
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

        <form action="/usr/article/Reply/WriteReply" method="post"
            onsubmit="return replyForm_onSubmit(this); return false;">
            <input type="hidden" name="relTypeCode" value="article" />
            <input type="hidden" name="relId" value="${foundArticle.id}" />
            <div class="mt-4 reply-border p-4">
                <div class="mb-2"><span>닉네임</span></div>
                <textarea class="textarea textarea-bordered textarea-lg w-full" name="body"
                    placeholder="댓글을 입력해보세요"></textarea>
                <div class="flex justify-end"><button class="btn btn-active btn-sm">작성</button></div>
            </div>
        </form>

    </div>
</section>

<div class="mt-3 text-sm">
    <button class="btn btn-active btn-sm" onclick="history.back();">뒤로가기</button>
    <a class="btn btn-active btn-sm" href="Modify?id=${foundArticle.id}">수정</a>
    <a class="btn btn-active btn-sm" href="doDelete?id=${foundArticle.id}"
        onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
</div>

<%@ include file="../../common/foot.jsp" %>