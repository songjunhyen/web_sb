package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.vo.Article;


//ArticleService 클래스: 
// 비즈니스 로직을 처리하는 서비스 클래스로, ArticleDao를 사용하여 데이터베이스 접근을 수행합니다. 
// Controller에서 요청을 받아 처리하고, DAO의 메서드를 호출하여 데이터를 처리합니다.

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    public Article writeArticle(String title, String body) {
        return articleDao.writeArticle(title, body);
    }

    public List<Article> getArticles() {
        return articleDao.getArticles();
    }

    public boolean deleteArticle(int id) {
        return articleDao.deleteArticle(id);
    }

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }

    public boolean updateArticle(int id, String title, String body) {
        return articleDao.updateArticle(id, title, body);
    }
}