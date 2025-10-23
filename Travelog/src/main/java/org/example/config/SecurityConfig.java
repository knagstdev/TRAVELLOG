package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // 공개 경로
                        .requestMatchers("/mypage").authenticated()
                        // 그 외 경로는 인증 필요
                        .anyRequest().permitAll()
                )
                // 일반 로그인 설정
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .usernameParameter("loginId")
                        .passwordParameter("password")
                        .failureUrl("/auth/login?error=true")
                        .permitAll()
                )
                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .permitAll()
                )
                // 보안토큰설정: `/inquiries/my/delete` 경로를 CSRF 검사 예외 목록에 추가
                .csrf(csrf -> csrf
                        // ✅ 문의 삭제 POST 요청에 대한 CSRF 검사를 무시합니다. (403 Forbidden 해결)
                        .ignoringRequestMatchers("/inquiries/my/delete", "/ping", "/api/footer/**")
                );
        return http.build();
    }
}
