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

//UsrArticleController 클래스: 
// 웹 요청을 처리하는 컨트롤러 클래스로, HTTP 요청을 받아 Service를 호출하고, 그 결과를 클라이언트에게 HTML 형태로 반환합니다. 각 요청에 따른 처리를 구현하고 있습니다.

@Controller
public class UsrArticleController {

	private ArticleService articleService;

	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@PostMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(@RequestParam String title, @RequestParam String body) {
		String writer = "tester";
		Article article = new Article(title, body, writer);
		articleService.writeArticle(article);
		return article;//서블릿이나 jsp가 받아서 사용
	}

	@GetMapping("/usr/article/showList")
	@ResponseBody
	public List<Article> showList() {
		return articleService.getArticles();
	}

	@GetMapping("/usr/article/detail")
	@ResponseBody
	public Object showDetail(@RequestParam int id) {
		Article foundArticle = articleService.getArticleById(id);
		if (foundArticle == null) {
			return id + "번 게시물은 존재하지 않습니다";
		}
		return foundArticle;
	}

	@PostMapping("/usr/article/modify")
	@ResponseBody
	public String doModify(@RequestParam int id, @RequestParam String title, @RequestParam String body) {
		Article foundArticle = articleService.getArticleById(id);
		if (foundArticle == null) {
			return id + "번 게시물은 존재하지 않습니다";
		}
		articleService.modifyArticle(foundArticle, title, body);
		return id + "번 게시물을 수정했습니다";
	}

	@DeleteMapping("/usr/article/delete")
	@ResponseBody
	public String doDelete(@RequestParam int id) {
		Article foundArticle = articleService.getArticleById(id);
		if (foundArticle == null) {
			return id + "번 게시물은 존재하지 않습니다";
		}
		articleService.deleteArticle(foundArticle);
		return id + "번 게시물을 삭제했습니다";
	}
}