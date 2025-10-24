package org.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.example.jpa.auth.service.CustomOAuth2UserService; // ✅ 추가
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // 인증 필요
                        .requestMatchers("/mypage").authenticated()
                        // 그 외 경로는 허용
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
                )
                .oauth2Login(oauth -> oauth
                        .loginPage("/login")
                        .userInfoEndpoint(user -> user.userService(customOAuth2UserService)) // ✅ 수정 완료
                        .defaultSuccessUrl("/", true)
                );
        return http.build();
    }
}
