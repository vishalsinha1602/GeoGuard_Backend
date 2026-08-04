package com.backend.geosentinel.util;


import com.backend.geosentinel.security.entity.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

public  class AppUtil {
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static void clearCookie(HttpServletResponse response, String cookieName) {

        Cookie cookie = new Cookie(cookieName, "");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/api/v1/auth");       // EXACT SAME PATH
        cookie.setMaxAge(0);

        response.addCookie(cookie);
    }

}
