package com.example.demo.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReplyService;
import com.example.demo.vo.Reply;
import com.example.demo.vo.Rq;

import util.Util;


public class UsrReplyController {

	private ReplyService replyService;
	private Rq rq;

	public UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}

	@GetMapping("/usr/article/Reply/getReply")
	@ResponseBody
	public String getLikePoint(String relTypeCode, int relId,Model model) {
		
		List<Reply> replyList = replyService.getReply(rq.getLoginedMemberId(), relTypeCode, relId);
	    model.addAttribute("replyList", replyList);
		return "reply/list";
	}

	@GetMapping("/usr/article/Reply/WriteReply")
	@ResponseBody
	public String doWrite(String body, String relTypeCode, int relId) {

		replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);

		int id = replyService.getLastInsertId();

		return Util.jsReplace(String.format("%d번 댓글을 작성했습니다", id), String.format("../article/detail?id=%d", relId));
	}
	
	@GetMapping("/usr/article/Reply/DeleteReply")
	@ResponseBody
	public String DeleteReply(String relTypeCode, int relId, boolean likePointBtn) {
		replyService.deleteReply(rq.getLoginedMemberId(), relTypeCode, relId);
		int id = replyService.getLastInsertId();
		return Util.jsReplace(String.format("%d번 댓글을 삭제했습니다", id), String.format("../article/detail?id=%d", relId));
	}
	

}
