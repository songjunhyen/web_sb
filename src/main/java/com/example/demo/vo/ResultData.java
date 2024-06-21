package com.example.demo.vo;

import lombok.Data;

@Data
public class ResultData<E> {
    private String resultCode;
    private String resultMsg;
    private E data;

    public static <E> ResultData<E> from(String code, String msg) {
        ResultData<E> result = new ResultData<>();
        result.resultCode = code;
        result.resultMsg = msg;
        result.data = null;

        return result;
    }

    public static <E> ResultData<E> from(String code, String msg, E data) {
        ResultData<E> result = new ResultData<>();
        result.resultCode = code;
        result.resultMsg = msg;
        result.data = data;

        return result;
    }
	
	public boolean isSuccess() {
		return this.resultCode.startsWith("S-");
	}
	public boolean isFail() {
		return isSuccess() == false;
	}
}
