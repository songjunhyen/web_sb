package com.example.demo.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.UserService;
import com.example.demo.vo.Rq;
import com.example.demo.vo.User;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import util.Util;

@Controller
public class UsrUserController {
	private UserService userService;
	private Rq rq;

	public UsrUserController(UserService userService, Rq rq) {
		this.userService = userService;
		this.rq = rq;
	}

	@GetMapping("/usr/home/login")
	public String loginPage() {
		return "usr/home/login"; // login.jsp 파일을 반환하도록 설정
	}

	@GetMapping("/usr/home/signup")
	public String sigbUp() {
		return "usr/home/signup"; // login.jsp 파일을 반환하도록 설정
	}

	@PostMapping("/usr/user/Signup")
	@ResponseBody
	public String Signup(@RequestParam String userid, @RequestParam String userpw, @RequestParam String userpw2,
			@RequestParam String nickname) {
		// 입력값 검증
		if (Util.isEmpty(userid)) {
			return Util.jsHistoryBack("아이디를 입력해주세요");
		}
		if (Util.isEmpty(userpw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요");
		}
		if (Util.isEmpty(nickname)) {
			return Util.jsHistoryBack("사용할 이름을 입력해주세요");
		}
		if (!userpw.equals(userpw2)) {
			return Util.jsHistoryBack("비밀번호가 일치하지 않습니다");
		}
		User foundUser = userService.getUserById(userid);
		if (foundUser == null) {
			// 회원가입 처리
			User user = new User(userid, userpw, nickname);
			userService.Signup(user);
			return Util.jsReplace("회원가입이 완료되었습니다.", "/usr/home/login");
		}
		return Util.jsHistoryBack("이미 존재하는 아이디입니다.");
	}

	@PostMapping("/usr/user/Login")
	@ResponseBody
	public String login(HttpServletRequest request, @RequestParam String userid, @RequestParam String userpw) {
		User user = userService.Login(userid, userpw);

		if (user == null) {
			return Util.jsHistoryBack("아이디 또는 비밀번호가 일치하지 않거나 입력하지 않으셨습니다.");
		}

		// 로그인 코드 생성
		String loginCode = UUID.randomUUID().toString();

		// 로그인 세션 관리를 위해 loginCode를 userService를 통해 업데이트
		userService.updateUserLoginSession(user, loginCode);

		rq.login(user);

		// 세션에 userId, loginCode 저장
		HttpSession session = request.getSession();
		session.setAttribute("loginCode", loginCode);

		// 로그인 성공 메시지와 사용자 정보 반환
		return Util.jsReplace("로그인 되었습니다.", "/usr/article/Main");
	}

	@PostMapping("/usr/user/Logout")
	@ResponseBody
	public String Logout(HttpServletRequest request) {
		rq.logout();
		String userid = rq.getloginId();
		User foundUser = userService.getUserById(userid);
		rq.logout();
		userService.Logout(foundUser);
		return Util.jsReplace("로그아웃 되었습니다.", "/usr/home/main");
	}

	@GetMapping("/usr/user/updated")
	public String Update() {
		return "usr/home/update";
	}

	@PostMapping("/usr/user/Update")
	@ResponseBody
	public String Update(@RequestParam String userid, @RequestParam String userpw,
			@RequestParam String pw, @RequestParam String nickname) {

		if (Util.isEmpty(userid)) {
			return Util.jsHistoryBack("아이디를 입력해주세요");
		}
		if (Util.isEmpty(userpw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요");
		}
		if (Util.isEmpty(nickname)) {
			return Util.jsHistoryBack("사용할 이름을 입력해주세요");
		}

		User foundUser = userService.getUserById(userid);

		String loginid = rq.getloginId();
		if (!foundUser.getUserid().equals(loginid)) {
			return Util.jsHistoryBack("본인의 아이디가 아닙니다.");
		}
		if (!foundUser.getUserpw().equals(userpw)) {
			return Util.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}
		foundUser.setUserpw(pw);
		foundUser.setNickname(nickname);
		userService.Update(foundUser);
		return Util.jsHistoryBack("수정되었습니다.");
	}

	@GetMapping("/usr/user/MyPage")
	public String Mypage(Model model) {
		String userid = rq.getloginId();
		User user = userService.getUserById(userid);
		model.addAttribute("user", user);

		return "usr/home/mypage";
	}

	@GetMapping("/usr/user/delete")
	public String DeLeTe() {
		return "/usr/home/delete";
	}

	@PostMapping("/usr/user/Delete")
	@ResponseBody
	public String Delete(@RequestParam String userid, @RequestParam String userpw) {
		User foundUser = userService.getUserById(userid);
		if (foundUser == null || foundUser.getUserid().equals(null)) {
			return Util.jsReplace("다른 사람의 정보에 접근할 권환이 없습니다.", "/usr/article/Main");
		} else {
			String sessionid = rq.getloginId();
			int sessionnum = rq.getLoginedMemberId();
			if (foundUser.getId() == sessionnum && foundUser.getUserid().equals(sessionid) && userid.equals(sessionid)
					&& foundUser.getUserpw().equals(userpw)) {
				userService.Delete(foundUser);
			} else {
				return Util.jsReplace("다른 사람의 정보에 접근할 권환이 없습니다.", "/usr/article/Main");
			}
			return Util.jsReplace("회원정보가 삭제 되었습니다.", "/usr/home/main");
		}
	}
}