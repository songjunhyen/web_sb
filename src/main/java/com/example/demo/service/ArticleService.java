package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.vo.Article;

import util.Util;

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

	public Article writeArticle(Article article, String boardid) {
		int boardid1 = Integer.parseInt(boardid);
		this.articleDao.writeArticle(article, boardid1);
		return article;
	}

	public List<Article> getArticles() {
		return this.articleDao.getAllArticles();
	}

	public Article getArticleById(int id) {
		Article article = articleDao.getArticleById(id);
		return article;
	}
	
	public void upviewArticleById(int id, String userId) {
		Article article = articleDao.getArticleById(id);
		if(!article.getWriter().equals(userId)) {
			articleDao.updateView(id);
		}
	}

	public void modifyArticle(String writer, String title, String body) {
		articleDao.updateArticle(writer, title, body);
	}

	public void deleteArticle(Article foundArticle) {
		articleDao.deleteArticle(foundArticle);
	}

	public List<Article> getArticleslist(String boardid) {
		if (Util.isEmpty(boardid) || boardid.equals("0")) {
			return articleDao.getArticleslist();
		} else {
			return articleDao.getArticleslist2(boardid);
		}
	}

	public List<Article> getArticleslistByKeyword(String keyword, String boardid) {
		int boardid1 = Integer.parseInt(boardid);
		if(boardid1==0) {
			return articleDao.getArticleslistByKeyword2(keyword);
		}
		return articleDao.getArticleslistByKeyword(keyword, boardid1);
	}


    public int getLikeCount(int articleId) {
        // 해당 articleId의 좋아요 수를 조회하는 로직 구현
        return articleDao.getLikeCount(articleId);
    }


	public int insertLike(String userId, int articleId, String reltypecode) {
        return articleDao.insertLike(userId, articleId, reltypecode);
    }

	public int updateLike(String userId, int articleId, String reltypecode) {
		// TODO Auto-generated method stub
		return articleDao.updateLike(userId, articleId, reltypecode);
	}

}