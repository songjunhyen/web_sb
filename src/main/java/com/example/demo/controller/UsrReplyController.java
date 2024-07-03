package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReplyService;
import com.example.demo.vo.Reply;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import util.Util;

@Controller
public class UsrReplyController {

	private ReplyService replyService;
	private Rq rq;

	public UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}

	@PostMapping("/usr/article/Reply/WriteReply")
	@ResponseBody
	public String writeReply(HttpServletRequest request, String relTypeCode, int relId, String body) {
		String userId = (String) request.getSession().getAttribute("userId");
		Reply reply = new Reply(rq.getLoginedMemberId(), relTypeCode, relId, body, userId);
	    
	    replyService.writeReply(reply);
	    return Util.jsReplace(String.format("댓글을 작성했습니다"), String.format("../detail?id=%d", relId));
	}
	
	@PostMapping("/usr/article/Reply/DeleteReply")
	@ResponseBody
	public String deleteReply(int replyId,String relTypeCode) {
		int userId = rq.getLoginedMemberId();
		
	    int replywriterid = replyService.getReplyWriterid(replyId);
	    
	    if (replywriterid!=userId) {
	    	return Util.jsReplace(String.format("삭제할 권환이 없습니다."), String.format("../detail?id=%d", replyId));
	    }
	    
	    replyService.deleteReply(userId, relTypeCode,replyId);
	    return Util.jsReplace(String.format("삭제되었습니다"), String.format("../detail?id=%d", replyId));
	}
	
}
