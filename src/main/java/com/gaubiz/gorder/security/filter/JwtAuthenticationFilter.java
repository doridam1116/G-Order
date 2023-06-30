package com.gaubiz.gorder.security.filter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.gaubiz.gorder.security.jwt.JwtProperties;
import com.gaubiz.gorder.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static com.gaubiz.gorder.config.PropertyConfig.getMessageSource;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractTokenFromHeader(request);

        if (isLoginRequest(request) || request.getRequestURI().equals("/swagger-ui/*")) {
            filterChain.doFilter(request, response);
            return;
        } else if (token == null || !token.startsWith(JwtProperties.TOKEN_PREFIX)) {
            handleAuthenticationError(response);
            return;
        }
        try {
            token = token.substring(JwtProperties.TOKEN_PREFIX.length());

            // 토큰 검증이 성공한 경우
            // 다음 필터로 진행
            if (jwtProvider.validateToken(token)) {
                Claims claims = jwtProvider.parseJwtToken(token);
                String type = claims.get("type", String.class);

                if(type.equals("MASTER")){
                    filterChain.doFilter(request,response);
                }else if(type.equals("SUB")){
                    String requestURI = request.getRequestURI();

                    if (requestURI.equals("/orders/new") ||
                            requestURI.startsWith("/orders/") ||
                            (requestURI.equals("/products") && request.getParameter("categoryNo") != null)) {
                        filterChain.doFilter(request, response);
                        return;
                    }
                }

                handleAuthorizationError(response);
            } else {
                // 토큰 검증 실패
                handleAuthenticationError(response);
            }
        } catch (TokenExpiredException e) {
            // 토큰 파싱 등의 오류
            handleAuthenticationError(response);
        }
    }

    private boolean isLoginRequest(HttpServletRequest request) {
//        return request.getRequestURI().equals("/account/login") || request.getRequestURI().equals("/account/register") && request.getMethod().equals(HttpMethod.POST.name());
        return true;
    }

    private void handleAuthenticationError(HttpServletResponse response) throws IOException {
        // 인증 오류에 대한 처리
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(getMessageSource().getMessage("HTTP_UNAUTHORIZED",null, Locale.getDefault()));
    }

    private void handleAuthorizationError(HttpServletResponse response) throws IOException {
        // 접근 권한 오류에 대한 처리
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(getMessageSource().getMessage("HTTP_FORBIDDEN",null, Locale.getDefault()));
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        // header의 key값이 Authorization 인 값을 요청에서 가져온다.
        String headerValue = request.getHeader("Authorization");
        // 값은 값이 null이 아니거나 Bearer 로 시작한다면 값을 리턴한다.
        if (headerValue != null && headerValue.startsWith("Bearer ")) {
            return headerValue;
        }
        return null;
    }

}
