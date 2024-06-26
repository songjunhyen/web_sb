package com.example.demo.vo;

import java.io.IOException;

import util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

public class Rq {
	@Getter
	private int loginedMemberId;
	private HttpServletResponse resp;

	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		this.resp = resp;

		HttpSession session = req.getSession();
		Integer loginedMemberId = (Integer) session.getAttribute("loginedMemberId");
		if (loginedMemberId != null) {
			this.loginedMemberId = loginedMemberId;
		} else {
			this.loginedMemberId = 0; // 기본값 설정
		}
	}

	public void jsPrintReplace(String msg, String uri) {
		resp.setContentType("text/html; charset=UTF-8;");

		try {
			resp.getWriter().append(Util.jsReplace(msg, uri));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}