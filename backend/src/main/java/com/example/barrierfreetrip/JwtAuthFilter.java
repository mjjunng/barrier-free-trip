package com.example.barrierfreetrip;

import com.example.barrierfreetrip.member.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // get token from header
        String token = tokenService.extractToken(request);

        // if token is valid
        if ((token != null) && (tokenService.verifyToken(token))) {
            // get member info from token
            Authentication authentication = tokenService.getAuthentication(token);

            // save member in securityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        // execute next filter
        filterChain.doFilter(request, response);
    }
}
