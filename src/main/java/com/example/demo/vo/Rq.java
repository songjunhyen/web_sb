package com.example.demo.vo;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
	@Getter
	private int loginedMemberId;
	private HttpServletResponse resp;
	private HttpSession session;

	public Rq(HttpServletRequest req, HttpServletResponse resp) {

		this.resp = resp;
		this.session = req.getSession();
		int loginedMemberId = 0;

		if (this.session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) this.session.getAttribute("loginedMemberId");
			req.setAttribute("rq", this);
		}

		this.loginedMemberId = loginedMemberId;
	}

	public void jsPrintReplace(String msg, String uri) {
		resp.setContentType("text/html; charset=UTF-8;");

		try {
			resp.getWriter().append(Util.jsReplace(msg, uri));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void login(User user) {
		this.session.setAttribute("loginedMemberId", user.getId());
	}

	public void logout() {
		this.session.removeAttribute("loginedMemberId");
	}
	
	public void init() {
		//아무 기능 없어도 됨 의존성 주입을 의해 호출하여 인스턴스가 생성하기위한 메서드
	}
}