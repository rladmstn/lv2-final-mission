package finalmission.global.utils;

import jakarta.servlet.http.Cookie;

public final class CookieUtils {

    private CookieUtils() {
    }

    public static Cookie setToken(String token) {
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }
}
