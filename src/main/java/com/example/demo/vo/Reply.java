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
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
	private int userId;
	private String relTypeCode;
	private int relId;
	private String body;
}
