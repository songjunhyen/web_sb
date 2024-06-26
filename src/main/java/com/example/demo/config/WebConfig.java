package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.BeforeActionInterceptor;
import com.example.demo.interceptor.NeedLoginInterceptor;
import com.example.demo.interceptor.NeedLogoutInterceptor;


@Configuration
public class WebConfig implements WebMvcConfigurer {

	private BeforeActionInterceptor beforActionInterceptor;
	private NeedLoginInterceptor needLoginInterceptor;
	private NeedLogoutInterceptor needLogoutInterceptor;

	public WebConfig(BeforeActionInterceptor beforActionInterceptor, NeedLoginInterceptor needLoginInterceptor,
			NeedLogoutInterceptor needLogoutInterceptor) {
		this.beforActionInterceptor = beforActionInterceptor;
		this.needLoginInterceptor = needLoginInterceptor;
		this.needLogoutInterceptor = needLogoutInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");
	    
	    registry.addInterceptor(needLoginInterceptor)
	            .addPathPatterns("/usr/article/write")
	            .addPathPatterns("/usr/article/doWrite")
	            .addPathPatterns("/usr/article/modify")
	            .addPathPatterns("/usr/article/doModify")
	            .addPathPatterns("/usr/article/doDelete")
	            .addPathPatterns("/usr/article/Modify"); // 추가: 수정 페이지 접근 시 로그인 필요
	    
	    registry.addInterceptor(needLogoutInterceptor)
	            .addPathPatterns("/usr/member/join")
	            .addPathPatterns("/usr/member/doJoin")
	            .addPathPatterns("/usr/member/login")
	            .addPathPatterns("/usr/member/doLogin")
	            .addPathPatterns("/usr/home/signup"); // 추가: 회원가입 페이지 접근 시 로그아웃 상태 필요
	    
	    // 경로 수정: 로그아웃 동작 맞추기
	    //registry.addInterceptor(needLogoutInterceptor)
	    //.addPathPatterns("/usr/user/Logout");
	}
}