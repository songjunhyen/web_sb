package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    private int id;
    private LocalDateTime regDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();
    private int userId;
    private String relTypeCode;
    private int relId;
    private String body;
    private String writer;

    // 생성자 추가
    public Reply(int userId, String relTypeCode, int relId, String body,String writer) {
        this.userId = userId;
        this.relTypeCode = relTypeCode;
        this.relId = relId;
        this.body = body;
        this.writer = writer;
    }
    
    public String getForPrintBody() {
		return this.body.replaceAll("\n", "<br />");
	}
}