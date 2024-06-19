package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.vo.Article;


//ArticleDao 클래스: 
// 데이터베이스 접근을 담당하는 클래스로, 게시글에 대한 CRUD(Create, Read, Update, Delete) 기능을 구현합니다. 
// List를 사용하여 메모리 상에서 데이터를 관리하고 있습니다

@Repository
public class ArticleDao {
    private int lastArticleId = 0;
    private List<Article> articles = new ArrayList<>();

    public Article writeArticle(String title, String body) {
        Article article = new Article(++lastArticleId, title, body);
        articles.add(article);
        return article;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public boolean deleteArticle(int id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                articles.remove(article);
                return true;
            }
        }
        return false;
    }

    public Article getArticleById(int id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    public boolean updateArticle(int id, String title, String body) {
        for (Article article : articles) {
            if (article.getId() == id) {
                article.setTitle(title);
                article.setBody(body);
                return true;
            }
        }
        return false;
    }
}