package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.vo.Article;

//ArticleService 클래스: 
// 비즈니스 로직을 처리하는 서비스 클래스로, ArticleDao를 사용하여 데이터베이스 접근을 수행합니다. 
// Controller에서 요청을 받아 처리하고, DAO의 메서드를 호출하여 데이터를 처리합니다.

@Service
public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	public boolean CheckCode(String userid, String loginCode) {
		if (!loginCode.equals(articleDao.getLogin_codeByUserid(userid))) {
			return false;
		}
		return true;
	}
	
	public Article writeArticle(Article article) {
		this.articleDao.writeArticle(article);
		return article;
	}

	public List<Article> getArticles() {
		return this.articleDao.getAllArticles();
	}

	public Article getArticleById(int id) {
		return this.articleDao.getArticleById(id);
	}

	public void modifyArticle(String writer, Article foundArticle, String title, String body) {
		if (!writer.equals(foundArticle.getWriter())) {
			throw new IllegalStateException("작성자와 로그인 사용자가 일치하지 않습니다.");
		}
		this.articleDao.updateArticle(foundArticle, title, body);
	}

	public void deleteArticle(String writer, Article foundArticle) {
		if (!writer.equals(foundArticle.getWriter())) {
			throw new IllegalStateException("작성자와 로그인 사용자가 일치하지 않습니다.");
		}
		this.articleDao.deleteArticle(foundArticle);
	}

}