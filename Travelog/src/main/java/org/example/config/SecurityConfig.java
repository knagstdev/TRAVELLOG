package org.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.example.jpa.auth.service.CustomOAuth2UserService; // ✅ 추가
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // ✅ 1. CSRF 보호 해제 (API 테스트용)
                .csrf(csrf -> csrf.disable());
        http
                .authorizeHttpRequests(auth -> auth
                        // 회원가입/로그인은 누구나 가능
                        .requestMatchers("/login", "/join", "/auth/login").permitAll() // ✅ 여기 추가
                        // 인증 필요
                        .requestMatchers("/mypage").authenticated()
                        // 그 외 경로는 허용
                        .anyRequest().permitAll()
                )
                // 일반 로그인 설정
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .usernameParameter("loginId")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")  // ✅ 수정
                        .permitAll()
                )
                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                );


//
//                .oauth2Login(oauth -> oauth
//                        .loginPage("/login")
//                        .userInfoEndpoint(user -> user.userService(customOAuth2UserService)) // ✅ 수정 완료
//                        .defaultSuccessUrl("/", true)
//                );
        return http.build();
    }
    // ✅ BCryptPasswordEncoder 등록
    @Bean
    public org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder passwordEncoder() {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }
}
