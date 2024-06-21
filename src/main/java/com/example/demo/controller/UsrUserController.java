package com.example.demo.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.UserService;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import util.Util;

@Controller
public class UsrUserController {
	private UserService userService;

	public UsrUserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/usr/user/Signup")
	@ResponseBody
	public ResultData<?> Signup(@RequestParam String userid, @RequestParam String userpw, @RequestParam String userpw2,
			@RequestParam String nickname) {
		// 입력값 검증
		if (Util.isEmpty(userid)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		if (Util.isEmpty(userpw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요");
		}
		if (Util.isEmpty(nickname)) {
			return ResultData.from("F-3", "사용할 이름을 입력해주세요");
		}
		if (!userpw.equals(userpw2)) {
			return ResultData.from("F-4", "비밀번호가 일치하지 않습니다.");
		}
		User foundUser = userService.getUserById(userid);
		if (foundUser == null) {
			// 회원가입 처리
			User user = new User(userid, userpw, nickname);
			userService.Signup(user);
			return ResultData.from("S-1", "회원가입이 완료되었습니다.");
		}
		return ResultData.from("F-5", "이미 존재하는 아이디 입니다");
	}

	@PostMapping("/usr/user/Login")
	@ResponseBody
	public ResultData<User> Login(HttpServletRequest request, @RequestParam String userid,
			@RequestParam String userpw) {
		User user = userService.Login(userid, userpw);

		if (user == null) {
			return ResultData.from("F-8", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}

		// 로그인 코드 생성
		String login_code = UUID.randomUUID().toString();

		// 로그인 세션 관리를 위해 login_code를 userService를 통해 업데이트
		userService.updateUserLoginSession(user, login_code);

		// 세션에 userid, logincode저장
		HttpSession session = request.getSession();
		session.setAttribute("userId", userid);
		session.setAttribute("loginCode", login_code);

		// 로그인 성공 메시지와 사용자 정보 반환
		return ResultData.from("S-5", "로그인 되었습니다.", user);
	}

	@GetMapping("/usr/user/Logout")
	@ResponseBody
	public ResultData<?> Logout(String userid) {
		User foundUser = userService.getUserById(userid);
		if (foundUser == null) {
			return ResultData.from("F-6", "해당되는 유저가 존재하지 않습니다");
		}
		userService.Logout(foundUser);
		return ResultData.from("S-2", "로그아웃 되었습니다.");
	}

	@PostMapping("/usr/user/Update")
	@ResponseBody
	public ResultData<?> Update(@RequestParam String userid, @RequestParam String userpw,
			@RequestParam String nickname) {
		User foundUser = userService.getUserById(userid);
		if (foundUser == null) {
			return ResultData.from("F-6", "해당되는 유저가 존재하지 않습니다");
		}
		foundUser.setUserpw(userpw);
		foundUser.setNickname(nickname);
		userService.Update(foundUser);
		return ResultData.from("S-3", "수정 되었습니다.");
	}

	@DeleteMapping("/usr/user/delete")
	@ResponseBody
	public ResultData<?> Delete(@RequestParam String userid, @RequestParam String userpw) {
		User foundUser = userService.getUserById(userid);
		if (foundUser == null) {
			return ResultData.from("F-6", "유저가 존재하지 않습니다");
		}
		if (!foundUser.getUserpw().equals(userpw)) {
			return ResultData.from("F-7", "로그인 비밀번호가 일치하지 않습니다.");
		}
		userService.Delete(foundUser);

		return ResultData.from("S-4", "회원정보가 삭제 되었습니다.");
	}
}