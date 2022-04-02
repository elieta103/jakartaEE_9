package org.elhg.apiservlet.webapp.cookies.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public class LoginServiceImpl implements  LoginService{
    @Override
    public Optional<String> getUserName(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        // Arrays.stream(cookies).filter()....
        return  Arrays.asList(cookies).stream()
                .filter(cookie ->  cookie.getName().equals("username"))
                .map(Cookie::getValue)
                .findFirst();
    }
}
