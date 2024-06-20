package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.UserService;
import com.example.demo.vo.User;

import util.Util;

@Controller
public class UsrUserController {
	private UserService userService;

	public UsrUserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/usr/user/Signup")
	@ResponseBody
	public String Signup(@RequestParam String userid, @RequestParam String userpw, @RequestParam String userpw2,
			@RequestParam String nickname) {
		// 입력값 검증
		if (Util.isEmpty(userid) || Util.isEmpty(userpw) || Util.isEmpty(nickname)) {
			return "입력값을 모두 입력해주세요.";
		}
		if (!userpw.equals(userpw2)) {
			return "비밀번호가 일치하지 않습니다.";
		}
		User foundUser = userService.getUserById(userid);
		if (foundUser == null) {
			// 회원가입 처리
			User user = new User(userid, userpw, nickname);
			userService.Signup(user);
			return "회원가입이 완료되었습니다.";
		}
		return "이미 존재하는 유저입니다";
	}

	@PostMapping("/usr/user/Login")
	@ResponseBody
	public User Login(@RequestParam String userid, @RequestParam String userpw) {
		User user = userService.Login(userid, userpw);
		return user;// 서블릿이나 jsp가 받아서 사용
	}

	@GetMapping("/usr/user/Logout")
	@ResponseBody
	public String Logout(String userid) {
		User foundUser = userService.getUserById(userid);
		if (foundUser == null) {
			return "유저가 존재하지 않습니다";
		}
		userService.Logout(foundUser);
		return "로그아웃 되었습니다.";
	}

	@PostMapping("/usr/user/Update")
	@ResponseBody
	public String Update(@RequestParam String userid, @RequestParam String userpw, @RequestParam String nickname) {
		User foundUser = userService.getUserById(userid);
		if (foundUser == null) {
			return "유저가 존재하지 않습니다";
		}
		foundUser.setUserpw(userpw);
	    foundUser.setNickname(nickname);
		userService.Update(foundUser);
		return "수정되었습니다";
	}

	@DeleteMapping("/usr/user/delete")
	@ResponseBody
	public String Delete(@RequestParam String userid, @RequestParam String userpw) {
	    User foundUser = userService.getUserById(userid);
	    	    if (foundUser == null) {
	        return "유저가 존재하지 않습니다";
	    }	    
	    if (!foundUser.getUserpw().equals(userpw)) {
	        return "비밀번호가 일치하지 않습니다";
	    }	    	    
	    userService.Delete(foundUser);
	    
	    return "삭제했습니다";
	}
}