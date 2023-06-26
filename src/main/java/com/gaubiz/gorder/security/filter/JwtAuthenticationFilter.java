package com.gaubiz.gorder.security.filter;

import com.gaubiz.gorder.security.jwt.JwtProvider;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(isLoginRequest(request)){
            filterChain.doFilter(request,response);
            return;
        }
    }

    private boolean isLoginRequest(HttpServletRequest request) {
        return request.getRequestURI().equals("/account/login") || request.getRequestURI().equals("/account/register")  && request.getMethod().equals(HttpMethod.POST.name());
    }

}
