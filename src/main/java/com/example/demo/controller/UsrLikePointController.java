package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.LikePointService;
import com.example.demo.vo.LikePoint;
import com.example.demo.vo.Rq;

@Controller
public class UsrLikePointController {

	private LikePointService likePointService;
	private Rq rq;

	public UsrLikePointController(LikePointService likePointService, Rq rq) {
		this.likePointService = likePointService;
		this.rq = rq;
	}

	@GetMapping("/usr/article/likePoint/getLikePoint")
	@ResponseBody
	public int getLikePoint(String relTypeCode, int relId) {
		LikePoint haveLikepoint = likePointService.haveLikePoint(relTypeCode, relId);
		if (haveLikepoint != null) {	
			return likePointService.getLikePoint(relTypeCode, relId);
		}
		return 0;
	}

	@GetMapping("/usr/article/likePoint/doLikePoint")
	@ResponseBody
	public int doLikePoint(String relTypeCode, int relId	) {
		int loginMemberId = rq.getLoginedMemberId();
		boolean havelike = likePointService.haveLike(loginMemberId, relTypeCode, relId);
		if (!havelike) {
			likePointService.insertOrUpdateLikePoint(loginMemberId, relTypeCode, relId);
			return likePointService.getLikePoint(relTypeCode, relId);
		} else {
			return likePointService.getLikePoint(relTypeCode, relId);
		}
	}
}