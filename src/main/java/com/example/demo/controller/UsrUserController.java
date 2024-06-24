package com.example.demo.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/usr/home/login")
    public String loginPage() {
        return "usr/home/login"; // login.jsp 파일을 반환하도록 설정
    }
	@GetMapping("/usr/home/signup")
    public String sigbUp() {
        return "usr/home/signup"; // login.jsp 파일을 반환하도록 설정
    }
	
	@PostMapping("/usr/user/Signup")
	public String Signup(@RequestParam String userid, @RequestParam String userpw, @RequestParam String userpw2,
			@RequestParam String nickname, HttpServletRequest request) {
		// 입력값 검증
		if (Util.isEmpty(userid)) {
			ResultData.from("F-1", "아이디를 입력해주세요");
			request.setAttribute("errorMessage", "아이디를 입력해주세요");
			return "/usr/home/signup";
		}
		if (Util.isEmpty(userpw)) {
			ResultData.from("F-2", "비밀번호를 입력해주세요");
			request.setAttribute("errorMessage", "비밀번호를 입력해주세요");
			return "/usr/home/signup";
		}
		if (Util.isEmpty(nickname)) {
			ResultData.from("F-3", "사용할 이름을 입력해주세요");
			request.setAttribute("errorMessage", "사용할 이름을 입력해주세요");
			return "/usr/home/signup";
		}
		if (!userpw.equals(userpw2)) {
			ResultData.from("F-4", "비밀번호가 일치하지 않습니다.");
			request.setAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
			return "/usr/home/signup";
		}
		User foundUser = userService.getUserById(userid);
		if (foundUser == null) {
			// 회원가입 처리
			User user = new User(userid, userpw, nickname);
			userService.Signup(user);
			ResultData.from("S-1", "회원가입이 완료되었습니다.");
			return "redirect:/usr/home/login";
		}
		ResultData.from("F-5", "이미 존재하는 아이디 입니다");
		request.setAttribute("errorMessage", "이미 존재하는 아이디입니다.");
		return "/usr/home/signup";
	}

	@PostMapping("/usr/user/Login")
	public String Login(HttpServletRequest request, @RequestParam String userid,
			@RequestParam String userpw) {
		User user = userService.Login(userid, userpw);

		if (user == null) {
			ResultData.from("F-8", "아이디 또는 비밀번호가 일치하지 않습니다.");
			request.setAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "/usr/user/Login";
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
		ResultData.from("S-5", "로그인 되었습니다.", user);
		request.setAttribute("errorMessage", "로그인 되었습니다.");
		return "usr/article/main";
	}

	@PostMapping("/usr/user/Logout")
	public String Logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userId");
		User foundUser = userService.getUserById(userid);
		if (foundUser == null) {
			ResultData.from("F-6", "해당되는 유저가 존재하지 않습니다");
			redirectAttributes.addFlashAttribute("errorMessage", "해당되는 유저가 존재하지 않습니다");
			return "redirect:/usr/home/main";
		}
		session.invalidate();
		userService.Logout(foundUser);
		ResultData.from("S-2", "로그아웃 되었습니다.");
		redirectAttributes.addFlashAttribute("errorMessage", "로그아웃 되었습니다.");
		return "redirect:/usr/home/main";
	}

	@PostMapping("/usr/user/Update")
	public String Update(HttpServletRequest request, @RequestParam String userid, @RequestParam String userpw,
			@RequestParam String nickname) {
		User foundUser = userService.getUserById(userid);
		if (foundUser == null) {
			ResultData.from("F-6", "해당되는 유저가 존재하지 않습니다");
			request.setAttribute("errorMessage", "해당되는 유저가 존재하지 않습니다");
			return "/usr/article/Main";
		}
		foundUser.setUserpw(userpw);
		foundUser.setNickname(nickname);
		userService.Update(foundUser);
		ResultData.from("S-3", "수정 되었습니다.");
		request.setAttribute("errorMessage", "수정되었습니다.");
		return "/usr/article/Main";
	}

	@DeleteMapping("/usr/user/delete")
	public String Delete(HttpServletRequest request,@RequestParam String userid, @RequestParam String userpw) {
		User foundUser = userService.getUserById(userid);
		if (foundUser == null) {
			ResultData.from("F-6", "해당되는 유저가 존재하지 않습니다");
			request.setAttribute("errorMessage", "해당되는 유저가 존재하지 않습니다");
			return "/usr/article/Main";
		}
		if (!foundUser.getUserpw().equals(userpw)) {
			ResultData.from("F-7", "로그인 비밀번호가 일치하지 않습니다.");
			request.setAttribute("errorMessage", "로그인 비밀번호가 일치하지 않습니다.");
			return "/usr/article/Main";
		}
		userService.Delete(foundUser);
		ResultData.from("S-4", "회원정보가 삭제 되었습니다.");
		request.setAttribute("errorMessage", "회원정보가 삭제 되었습니다.");
		return "/usr/home/Main";
	}
}