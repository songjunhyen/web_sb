package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vo.Article;

@RestController
public class UsrArticleController {
	private int lastArticleId;
	private List<Article> articles;

	public UsrArticleController() {
		this.lastArticleId = 0;
		this.articles = new ArrayList<>();
	}

	public int getArticlessize() {
		return articles.size();
	}

	@GetMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {
		Article article = new Article(++lastArticleId, title, body);
		this.articles.add(article);
		article.getId();
		article.getTitle();
		article.getBody();
		return article;
	}

	@GetMapping("/usr/article/showList")
	@ResponseBody
	public List<Article> showList() {
		return this.articles;
	}

	@GetMapping("/usr/article/Delete")
	@ResponseBody
	public String Delete(@RequestParam int id) {
		int i = 0;
		while (i < articles.size()) {
			if (articles.get(i).getId() == id) {
				articles.remove(i);
				return id + "번 글이 삭제되었습니다.";
			}
			i++;
		}
		return id + "번 글을 찾을 수 없습니다.";
	}

	@GetMapping("/usr/article/Edit")
	@ResponseBody
	public String Edit(@RequestParam int id, @RequestParam String title, @RequestParam String body) {
		for (Article article : articles) {
			if (article.getId() == id) {
				article.setTitle(title);
				article.setBody(body);
				return id + "번 글이 수정되었습니다.";
			}
		}
		return id + "번 글을 찾을 수 없습니다.";
	}

	@GetMapping("/usr/article/Detail")
	@ResponseBody
	public Article Detail(@RequestParam int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				article.getTitle();
				article.getBody();
				return article;
			}
		}
		return null;
	}
}