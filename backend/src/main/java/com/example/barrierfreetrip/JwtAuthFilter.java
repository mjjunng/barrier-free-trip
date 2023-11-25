package com.example.barrierfreetrip;

import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.member.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean {
    private final TokenService tokenService;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // get token from header
        String token = ((HttpServletRequest) request).getHeader("Auth");

        // if token is valid
        if ((token != null) && (tokenService.verifyToken(token))) {
            // get member info from token
            Authentication authentication = tokenService.getAuthentication(token);

            // save member in securityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        // execute next filter
        chain.doFilter(request, response);
    }
}
