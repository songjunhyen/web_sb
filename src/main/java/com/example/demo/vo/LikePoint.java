package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikePoint {
	private int id;
	private int userId;
	private String relTypeCode;
	private int relId;
	private int point;
	
	
    // 생성자 추가
    public LikePoint(int userId, String relTypeCode, int relId, int point) {
        this.userId = userId;
        this.relTypeCode = relTypeCode;
        this.relId = relId;
        this.point = point;
    }
    
}