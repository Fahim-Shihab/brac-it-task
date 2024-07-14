package brac.it.sample.common;

public class Utils {
    public static boolean isOk(String s) {
        if (s != null && !s.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
