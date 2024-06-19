package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;


//UsrArticleController 클래스: 
// 웹 요청을 처리하는 컨트롤러 클래스로, HTTP 요청을 받아 Service를 호출하고, 그 결과를 클라이언트에게 HTML 형태로 반환합니다. 각 요청에 따른 처리를 구현하고 있습니다.

@RestController
@RequestMapping("/usr/article")
public class UsrArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/doWrite")
    @ResponseBody
    public String doWrite(@RequestParam String title, @RequestParam String body) {
        Article article = articleService.writeArticle(title, body);
        return "<html>" +
                "<body>" +
                "<h1>메인 페이지</h1>" +
                "<br>번호 : " + article.getId() + "</br>" +
                "<br>제목 : " + article.getTitle() + "</br>" +
                "<br>내용 : " + article.getBody() + "</br>" +
                "</body>" +
                "</html>";
    }

    @GetMapping("/showList")
    @ResponseBody
    public String showList() {
        List<Article> articles = articleService.getArticles();
        String htmlContent = "<html><body><h1>게시글 목록</h1>";

        for (Article article : articles) {
            htmlContent += "번호 : " + article.getId() +
                           " 제목 : " + article.getTitle() +
                           " 내용 : " + article.getBody() + "<br><br>";
        }

        htmlContent += "</body></html>";

        return htmlContent;
    }

    @GetMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam int id) {
        if (articleService.deleteArticle(id)) {
            return id + "번 글이 삭제되었습니다.";
        } else {
            return id + "번 글을 찾을 수 없습니다.";
        }
    }

    @PostMapping("/edit")
    @ResponseBody
    public String edit(@RequestParam int id, @RequestParam String title, @RequestParam String body) {
        if (articleService.updateArticle(id, title, body)) {
            return "<html>" +
                    "<body>" +
                    "<h1>메인 페이지</h1>" +
                    "<br>" + id + "번 글이 수정되었습니다.</br>" +
                    "<br>번호 : " + id + "</br>" +
                    "<br>제목 : " + title + "</br>" +
                    "<br>내용 : " + body + "</br>" +
                    "</body>" +
                    "</html>";
        } else {
            return id + "번 글을 찾을 수 없습니다.";
        }
    }

    @GetMapping("/detail")
    @ResponseBody
    public String detail(@RequestParam int id) {
        Article article = articleService.getArticleById(id);
        if (article != null) {
            return "<html>" +
                    "<body>" +
                    "<h1>메인 페이지</h1>" +
                    "<br>번호 : " + article.getId() + "</br>" +
                    "<br>제목 : " + article.getTitle() + "</br>" +
                    "<br>내용 : " + article.getBody() + "</br>" +
                    "</body>" +
                    "</html>";
        } else {
            return "해당 글이 존재하지 않습니다.";
        }
    }
}