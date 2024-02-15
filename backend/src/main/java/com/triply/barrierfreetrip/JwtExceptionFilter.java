package com.triply.barrierfreetrip;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, e);
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable e) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json; charset=UTF-8");

        String code = "";
        if (e.getMessage().equals("유효하지 않은 토큰")) {
            code = "600";
        } else if (e.getMessage().equals("헤더에 토큰 없음")) {
            code = "601";
        } else {
          code = "602";
        }
        JwtExceptionResponse jwtExceptionResponse = new JwtExceptionResponse(e.getMessage(), HttpStatus.UNAUTHORIZED, code);
        response.getWriter().write(jwtExceptionResponse.convertToJson(jwtExceptionResponse));
    }
}
