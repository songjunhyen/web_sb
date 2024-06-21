package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Util {
	public static boolean isEmpty(String key) {
		return key == null || key.length() == 0;
	}

	public static String formatDate(String dateStr) {
		if (isEmpty(dateStr)) {
			return "";
		}
		try {
			// 입력된 날짜 문자열을 LocalDateTime 객체로 파싱
			LocalDateTime dateTime = LocalDateTime.parse(dateStr);

			// 원하는 출력 형식 지정
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

			// 지정된 형식으로 날짜 문자열 변환
			return dateTime.format(formatter);
		} catch (DateTimeParseException e) {
			// 날짜 문자열 파싱에 실패한 경우 빈 문자열 반환
			return "";
		}
	}

}