package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int id;
    private LocalDateTime regDate; // LocalDateTime을 사용하여 날짜와 시간을 처리
    private LocalDateTime updateDate; // updateDate는 수정 시 자동 업데이트되어야 함
    private String userid;
    private String userpw;
    private String nickname;

    public User(String userid, String userpw, String nickname) {
        this.userid = userid;
        this.userpw = userpw;
        this.nickname = nickname;
        this.regDate = LocalDateTime.now(); // 현재 시간으로 설정
        this.updateDate = LocalDateTime.now(); // 현재 시간으로 설정
    }
}