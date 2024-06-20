package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
    @GetMapping("/usr/home/main")
    @ResponseBody
    public String showMain() {
    	return "<html>" +
                "<body>" +
                "<h1>메인 페이지</h1>" +
                "<form action=\"/usr/user/Login\" method=\"post\">\r\n"+
                "<label for=\"userid\">아이디:</label><br>\r\n"+
                "<input type=\"text\" id=\"userid\" name=\"userid\" placeholder=\"아이디를 입력해주세요\"><br><br>\r\n"+
                "<label for=\"userpw\">비밀번호:</label><br>\r\n"+
                "<input type=\"password\" id=\"userpw\" name=\"userpw\" placeholder=\"비밀번호를 입력해주세요\"><br><br>\r\n"+
                "<input type=\"submit\" value=\"로그인\">\r\n"+
                "</form>"+
                "<br>"+
                "<form action=\"/usr/user/Signup\" method=\"post\">\r\n"+
                "<label for=\"userid\">아이디:</label><br>\r\n"+
                "<input type=\"text\" id=\"userid\" name=\"userid\" placeholder=\"아이디를 입력해주세요\"><br><br>\r\n"+
                "<label for=\"userpw\">비밀번호:</label><br>\r\n"+
                "<input type=\"password\" id=\"userpw\" name=\"userpw\" placeholder=\"비밀번호를 입력해주세요\"><br><br>\r\n"+
                "<label for=\"userpw2\">비밀번호 확인:</label><br>\r\n"+
                "<input type=\"password\" id=\"userpw\" name=\"userpw2\" placeholder=\"비밀번호를 입력해주세요\"><br><br>\r\n"+
                "<label for=\"nickname\">닉네임:</label><br>\r\n"+
                "<input type=\"text\" id=\"nickname\" name=\"nickname\" placeholder=\"닉네임을 입력해주세요\"><br><br>\r\n"+
                "<input type=\"submit\" value=\"회원가입\">\r\n"+
                "</form>"+
                "<br>"+
                "<a href=\"/usr/user/Logout\">로그아웃</a><br><br>\r\n"+
                "</body>" +
                "</html>";
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