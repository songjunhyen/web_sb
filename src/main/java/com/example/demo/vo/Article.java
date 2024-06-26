package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private int id;
    private String title;
    private String body;
    private String writer;
    private LocalDateTime regDate; // LocalDateTime을 사용하여 날짜와 시간을 처리
    private LocalDateTime updateDate; // updateDate는 수정 시 자동 업데이트되어야 함
    private int viewcount;

    public Article(String title, String body, String writer) {    	
        this.title = title;
        this.body = body;
        this.writer = writer;
        this.regDate = LocalDateTime.now(); // 현재 시간으로 설정
        this.updateDate = LocalDateTime.now(); // 현재 시간으로 설정
        this.viewcount = 0; // 조회수 기본값 설정
    }
}