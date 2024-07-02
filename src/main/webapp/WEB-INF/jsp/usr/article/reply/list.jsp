<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:forEach var="reply" items="${replyList}">
    <div class="reply-item">
        <p>${reply.body}</p>
        <p class="small">${reply.regDate}</p>
    </div>
</c:forEach>