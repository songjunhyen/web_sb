package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//객체를 만든 적 없는데 알아서 초기화한다.
//직접 객체를 만들지 않아도 스프링의 어노케이션이 만들어낸다(제어의 역전)
@Controller
public class UsrHomeController {
	private int num;

	UsrHomeController() {
		this.num = 0;
	}

	@GetMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "안녕하세요";
	}

	@GetMapping("/usr/home/main2")
	@ResponseBody
	public String showMain2() {
		return Integer.toString(num++);
	}
}