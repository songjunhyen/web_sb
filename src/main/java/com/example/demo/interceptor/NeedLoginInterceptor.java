package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor {
	
	//Rq 클래스를 의존성 주입 받고 있다. 의존성 주입은 생성자를 통해 이루어지며, 이를 통해 Rq 객체를 인스턴스화하고 필드로 할당
	private Rq rq;
	public NeedLoginInterceptor(Rq rq) {
		this.rq = rq;
	}//인스턴스가 생성되기 위해서는 한 번 이상 호출해야 인스턴스가 생성된다.

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (rq.getLoginedMemberId() == 0) {
			rq.jsPrintReplace("로그인 후 이용해주세요", "/");
			return false;
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}