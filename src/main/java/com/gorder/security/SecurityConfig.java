package com.gorder.security;

import com.gorder.security.filter.JwtAuthenticationFilter;
import com.gorder.security.jwt.JwtProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    public SecurityConfig(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

        // csrf 비활성화
        security.csrf().disable()
                // JWT 로그인을 위한 비활성화
                .formLogin().disable()
                .antMatcher("/swagger-ui/index.html")
                // JWT 인증을 위한 필터 설정
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

        return security.build();
    }
}
