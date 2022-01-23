package com.schwarz.onlinelibrary.security;

import com.schwarz.onlinelibrary.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
        if (request.getRequestURI().contains("books")) {
            String token = request.getHeader("token");
            if (!tokenStore.isValidToken(token)) {
                throw new InvalidCredentialsException("User not logged in or token expired.");
            }
        }
        return true;
    }
}