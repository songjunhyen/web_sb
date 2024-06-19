package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//객체를 만든 적 없는데 알아서 초기화한다.
//직접 객체를 만들지 않아도 스프링의 어노케이션이 만들어낸다(제어의 역전)
@Controller
public class UsrHomeController {
	private String url1;
	private String url2;
	private String url3;
	private String url4;
	private String url5;
	int num = 1;
	private final UsrArticleController articleController;

	public UsrHomeController(UsrArticleController articleController) {
        this.articleController = articleController;
		this.url1 = "/usr/article/doWrite";
		this.url2 = "/usr/article/showList";
		this.url3 = "/usr/article/Detail";
		this.url4 = "/usr/article/Edit";
		this.url5 = "/usr/article/Delete";
	}

	private void addTestData() {
		while (articleController.showList().size() < 5) {
            articleController.doWrite("testtitle" + num++, "testbody");
        }
	}

	@GetMapping("/usr/home/main")
    @ResponseBody
    public String showMain() {    
		addTestData();
    	return "<html>" +
                "<body>" +
                "<h1>메인 페이지</h1>" +
                "<a href=\"" + url1 + "\">글 작성하기</a><br>" +
                "<a href=\"" + url2 + "\">글 목록 보기</a><br><br>" +
                "<form action=\"" + url3 + "\" method=\"GET\">" +
                "글 번호: <input type=\"text\" name=\"id\"><br>" +
                "<input type=\"submit\" value=\"글 상세 보기\">" +
                "</form><br>" +
                "<form action=\"" + url4 + "\" method=\"GET\">" +
                "글 번호: <input type=\"text\" name=\"id\"><br>" +
                "제목: <input type=\"text\" name=\"title\"><br>" +
                "내용: <input type=\"text\" name=\"body\"><br>" +
                "<input type=\"submit\" value=\"글 수정하기\">" +
                "</form><br>" +
                "<form action=\"" + url5 + "\" method=\"GET\">" +
                "글 번호: <input type=\"text\" name=\"id\"><br>" +
                "<input type=\"submit\" value=\"글 삭제하기\">" +
                "</form>" +
                "</body>" +
                "</html>";

	}
}
