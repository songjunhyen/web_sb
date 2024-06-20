package util;

public class Util {
    public static boolean isEmpty(String key) {
        if (key == null || key.length() == 0) {
            return true; // key가 null이거나 길이가 0인 경우 true 반환 (공백임)
        } else {
            return false; // 그 외의 경우 false 반환 (공백이 아님)
        }
    }
}