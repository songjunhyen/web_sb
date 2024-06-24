package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
    @GetMapping("/usr/home/main")
    public String showMain() {
    	return "usr/home/main";
    }  
    @GetMapping("/")
    public String showRoot() {
    	return "redirect:/usr/home/main";
    }
	
    @GetMapping("/usr/home/main2")
    @ResponseBody
    public String showMain2() {
    	return "<html>" +
                "<body>" +
                "<h1>메인 페이지</h1>" +
                "<form action=\"/usr/article/doWrite\" method=\"post\">\r\n"+
                "<label for=\"title\">Title:</label><br>\r\n"+
                "<input type=\"text\" id=\"title\" name=\"title\" placeholder=\"제목을 입력하세요\"><br>\r\n"+
                "<label for=\"body\">Body:</label><br>\r\n"+
                "<textarea id=\"body\" name=\"body\" placeholder=\"내용을 입력하세요\"></textarea><br><br>\r\n"+
                "<input type=\"submit\" value=\"Submit\">\r\n"+
                "</form> "+
                "<a href=\"/usr/article/showList\">글 목록 보기</a><br><br>" +
                "<form action=\"/usr/article/detail\" method=\"GET\">" +
                "글 번호: <input type=\"text\" name=\"id\"><br>" +
                "<input type=\"submit\" value=\"글 상세 보기\">" +
                "</form><br>" +
                "<form action=\"/usr/article/Search\" method=\"GET\">" +
                "키워드: <input type=\"text\" name=\"keyword\"><br>" +
                "<input type=\"submit\" value=\"검색하기\">" +
                "</form>" +
                "<form action=\"/usr/article/Modify\" method=\"POST\">" +
                "글 번호: <input type=\"text\" name=\"id\"><br>" +
                "제목: <input type=\"text\" name=\"title\"><br>" +
                "내용: <input type=\"text\" name=\"body\"><br>" +
                "<input type=\"submit\" value=\"글 수정하기\">" +
                "</form><br>" +
                "<form action=\"/usr/article/Delete\" method=\"GET\">" +
                "글 번호: <input type=\"text\" name=\"id\"><br>" +
                "<input type=\"submit\" value=\"글 삭제하기\">" +
                "</form>" +
                "<br></br>"+
                "</body>" +
                "</html>";
    }  
}