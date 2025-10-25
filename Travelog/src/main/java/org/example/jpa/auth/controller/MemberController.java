package org.example.jpa.auth.controller;

import org.example.jpa.auth.service.MemberService;
import org.example.jpa.auth.service.oauth.OAuth2UserInfo;
import org.example.jpa.auth.service.oauth.GoogleOAuth2UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/login/oauth2/code/google")
    public String oauth2LoginCallback(OAuth2AuthenticationToken authentication) {
        OAuth2User principal = authentication.getPrincipal();
        OAuth2UserInfo userInfo = new GoogleOAuth2UserInfo(principal.getAttributes()); // 구글 로그인 정보 처리

        // 소셜 로그인 후 사용자 정보 처리
        memberService.processOAuth2User(userInfo);

        return "redirect:/home"; // 홈 페이지로 리다이렉트
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "login"; // 로그인 페이지로 리다이렉트
    }

    // 회원가입 페이지
    @GetMapping("/join")
    public String join() {
        return "join"; // 회원가입 페이지로 리다이렉트
    }

    // 아이디 찾기 페이지
    @GetMapping("/findId")
    public String findId() {
        return "findId"; // 아이디 찾기 페이지로 리다이렉트
    }

    // 비밀번호 찾기 페이지
    @GetMapping("/findPw")
    public String findPw() {
        return "findPw"; // 비밀번호 찾기 페이지로 리다이렉트
    }


}
