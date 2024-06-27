package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpServletRequest;
import util.Util;

//UsrArticleController 클래스: 
// 웹 요청을 처리하는 컨트롤러 클래스로, HTTP 요청을 받아 Service를 호출하고, 그 결과를 클라이언트에게 HTML 형태로 반환합니다. 각 요청에 따른 처리를 구현하고 있습니다.
//세션에 저장된 정보랑 테이블에 저장된 정보랑 비교하는 코드 추가하여 보안상 변조를 감지하는 부분 추가 예정
@Controller
public class UsrArticleController {

	private ArticleService articleService;

	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@GetMapping("/usr/article/Main")
	public String loginPage() {
		return "usr/article/main"; // login.jsp 파일을 반환하도록 설정
	}

	@GetMapping("/usr/article/Write")
	public String Write() {
		return "usr/article/write"; // login.jsp 파일을 반환하도록 설정
	}

	@PostMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(HttpServletRequest request, @RequestParam String title, @RequestParam String body, @RequestParam String boardid) {
		String writer = (String) request.getSession().getAttribute("userId");
		checking(request, writer);

		if (Util.isEmpty(title) || Util.isEmpty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		if(Util.isEmpty(boardid)) {
			return Util.jsHistoryBack("게시판을 선택해주세요");
		}

		Article article = new Article(title, body, writer);
		articleService.writeArticle(article, boardid);
		return Util.jsReplace("작성되었습니다.", "Main");
	}

	@GetMapping("/usr/article/showList")
	public String showList(HttpServletRequest request, Model model,@RequestParam String boardid) {
		String userId = (String) request.getSession().getAttribute("userId");
		checking(request, userId);

		List<Article> articles = articleService.getArticleslist(boardid);
	   
		model.addAttribute("boardid", boardid);
		model.addAttribute("articles", articles);

		return "usr/article/articlelist";
	}

	@GetMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model,
			@RequestParam int id) {
		String userId = (String) request.getSession().getAttribute("userId");
		checking(request, userId);

		Article foundArticle = articleService.getArticleById(id);
		if (foundArticle == null) {
			ResultData.from("F-8", "해당되는 게시글이 존재하지 않습니다");
			redirectAttributes.addFlashAttribute("errorMessage", "해당되는 게시글이 존재하지 않습니다");
			return "redirect:usr/article/articlelist";
		}
		ResultData.from("S-a2", "게시글확인", foundArticle);
		model.addAttribute("foundArticle", foundArticle);
		return "usr/article/Detail";
	}

	@GetMapping("/usr/article/Search")
	public String searchList(HttpServletRequest request, Model model, String keyword) {
		String userId = (String) request.getSession().getAttribute("userId");
		checking(request, userId);

		if (Util.isEmpty(keyword)) {
			ResultData.from("F-1", "검색 키워드를 입력하세요.", null);
			request.setAttribute("errorMessage", "검색 키워드를 입력하세요.");
			return "usr/article/main";
		}

		List<Article> articles = articleService.getArticleslistByKeyword(keyword);
		ResultData.from("S-a3", "검색결과", articles);
		model.addAttribute("articles", articles);
		return "usr/article/articlelist";
	}

	@GetMapping("/usr/article/Modify")
	public String Modify(HttpServletRequest request, RedirectAttributes redirectAttributes, @RequestParam int id,
			Model model) {
		String userId = (String) request.getSession().getAttribute("userId");
		checking(request, userId);

		Article foundArticle = articleService.getArticleById(id);

		if (!foundArticle.getWriter().equals(userId)) {
			request.setAttribute("errorMessage", "수정할 권한이 없습니다.");
			return "usr/article/articlelist";
		}
		model.addAttribute("foundArticle", foundArticle);
		return "usr/article/modify";
	}

	@PostMapping("/usr/article/modify")
	@ResponseBody
	public String doModify(HttpServletRequest request, @RequestParam int id,
			@RequestParam String title, @RequestParam String body) {
		String userId = (String) request.getSession().getAttribute("userId");

		checking(request, userId);

		Article foundArticle = articleService.getArticleById(id);

		// 세션에 저장된 userId와 Article의 작성자(writer)를 비교합니다.
		if (!userId.equals(foundArticle.getWriter())) {
			return Util.jsReplace("작성자만 수정할 수 있습니다.","/usr/article/showList");
		}

		articleService.modifyArticle(userId, title, body);
		return Util.jsReplace("수정되었습니다.","/usr/article/showList");
	}

	@GetMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest request, int id) {
		String userId = (String) request.getSession().getAttribute("userId");

		checking(request, userId);

		Article foundArticle = articleService.getArticleById(id);
		if (!userId.equals(foundArticle.getWriter())) {
			return Util.jsReplace("작성자만 삭제할 수 있습니다.", "/usr/article/showList");
		}
		articleService.deleteArticle(foundArticle);

		return Util.jsReplace(String.format("%d번 게시물을 삭제했습니다", id), "showList");
	}

	private ResultData<Boolean> checking(HttpServletRequest request, String userId) {
		String loginCode = (String) request.getSession().getAttribute("loginCode");
		if (Util.isEmpty(loginCode) || Util.isEmpty(userId)) {
			return ResultData.from("F-0", "세션 정보가 없습니다. 다시 로그인 해주세요.", false);
		}
		boolean isCodeValid = articleService.CheckCode(userId, loginCode);
		if (!isCodeValid) {
			return ResultData.from("F-3", "변조가 감지되었습니다", false);
		}
		return ResultData.from("S-0", "안전", true);
	}
}