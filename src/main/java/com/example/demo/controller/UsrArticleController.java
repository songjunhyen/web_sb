package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

//UsrArticleController 클래스: 
// 웹 요청을 처리하는 컨트롤러 클래스로, HTTP 요청을 받아 Service를 호출하고, 그 결과를 클라이언트에게 HTML 형태로 반환합니다. 각 요청에 따른 처리를 구현하고 있습니다.
//세션에 저장된 정보랑 테이블에 저장된 정보랑 비교하는 코드 추가하여 보안상 변조를 감지하는 부분 추가 예정
@Controller
public class UsrArticleController {

	private ArticleService articleService;

	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@PostMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<Article> doWrite(@RequestParam String title, @RequestParam String body) {
		String writer = "tester"; // 세션에 저장된 유저id로 변경예정
		Article article = new Article(title, body, writer);
		articleService.writeArticle(article);
		return ResultData.from("S-a1", "작성되었습니다.", article);// 서블릿이나 jsp가 받아서 사용
	}

	@GetMapping("/usr/article/showList")
	@ResponseBody
	public ResultData<List<Article>> showList() {
		List<Article> articles = articleService.getArticles();
		return ResultData.from("S-a2", "", articles);
	}

	@GetMapping("/usr/article/detail")
	@ResponseBody
	public ResultData<Article> showDetail(@RequestParam int id) {
		Article foundArticle = articleService.getArticleById(id);
		if (foundArticle == null) {
			return ResultData.from("F-8", "해당되는 게시글이 존재하지 않습니다");
		}
		return ResultData.from("S-a2", "작성되었습니다.", foundArticle);
	}

	@PostMapping("/usr/article/modify")
	@ResponseBody
	public ResultData<?> doModify(@RequestParam int id, @RequestParam String title, @RequestParam String body) {
		String writer = "tester"; // 세션에 저장된 유저id로 변경예정

		Article foundArticle = articleService.getArticleById(id);
		if (foundArticle == null) {
			return ResultData.from("F-8", "해당되는 게시글이 존재하지 않습니다");
		}
		try {
			articleService.modifyArticle(writer, foundArticle, title, body);
			return ResultData.from("S-3", "수정 되었습니다.");
		} catch (IllegalStateException e) {
			return ResultData.from("F-9", e.getMessage());
		}
	}

	@DeleteMapping("/usr/article/delete")
	@ResponseBody
	public ResultData<?> doDelete(@RequestParam int id) {
		String writer = "tester"; // 세션에 저장된 유저id로 변경예정

		Article foundArticle = articleService.getArticleById(id);
		if (foundArticle == null) {
			return ResultData.from("F-8", "해당되는 게시글이 존재하지 않습니다");
		}
		try {
			articleService.deleteArticle(writer, foundArticle);
			return ResultData.from("S-4", "삭제 되었습니다.");
		} catch (IllegalStateException e) {
			return ResultData.from("F-9", e.getMessage());
		}
	}
}