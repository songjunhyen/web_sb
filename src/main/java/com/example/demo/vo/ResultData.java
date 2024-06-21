package com.example.demo.vo;

import lombok.Data;

@Data
public class ResultData {
	private String resultCode;
	private String resultMsg;
	private Object data;
	
	// 생성자로도 가능하지만, 생성자로 하면 객체를 만들어야 생성되서
	// 객체 없이 가능하도록 static으로 생성
	public static ResultData from(String code, String msg) { //2개 데이터을 받았을경우
		ResultData result = new ResultData();
		result.resultCode = code;
		result.resultMsg = msg;
		result.data = null;
		
		return result;
	}

	public static ResultData from(String code, String msg, Object data) { //3개 데이터을 받았을경우
		ResultData result = new ResultData();
		result.resultCode = code;
		result.resultMsg = msg;
		result.data = data;
		
		return result;
	}
	
	public boolean isSuccess() {
		return this.resultCode.startsWith("S-");
	}
	public boolean isFail() {
		return this.resultCode.startsWith("F-");
	}
}
