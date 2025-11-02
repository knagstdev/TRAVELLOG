package org.example.jpa.auth.controller;

import oracle.jdbc.proxy.annotation.Post;
import org.example.jpa.auth.dto.MemberLoginReq;
import org.example.jpa.auth.dto.MemberRes;
import org.example.jpa.auth.dto.MemberSaveReq;
import org.example.jpa.auth.service.MemberService;
import org.example.jpa.auth.service.oauth.OAuth2UserInfo;
//import org.example.jpa.auth.service.oauth.GoogleOAuth2UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;


    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // 로그인 페이지로 리다이렉트
    }

    @PostMapping("/login")
    public String login(MemberLoginReq req) {
        System.out.println("💡 로그인 요청: " + req.getLoginId());

        try {
            MemberRes res = memberService.login(req);
            System.out.println("✅ 로그인 성공: " + res.getNickname());
            return "redirect:/"; // 로그인 성공 시 메인으로 이동
        } catch (RuntimeException e) {
            System.out.println("❌ 로그인 실패: " + e.getMessage());
            return "redirect:/login?error=true"; // 실패 시 로그인 페이지로
        }
    }

    /** 아이디 중복 검사 */
    @GetMapping("/checkId")
    public ResponseEntity<Boolean> checkId(@RequestParam String loginId) {
        boolean exists = memberService.existsByLoginId(loginId);
        return ResponseEntity.ok(exists);
    }

    /** 닉네임 중복 검사 */
    @GetMapping("/checkNickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam String nickname) {
        boolean exists = memberService.existsByNickname(nickname);
        return ResponseEntity.ok(exists);
    }


    @GetMapping("/join")
    public String joinPage() {
        return "join"; // join.jsp 열기
    }

    // 회원가입 페이지
    @PostMapping("/join")
    public String join(MemberSaveReq req) { // 자동 매핑됨
        memberService.join(req);
        return "redirect:/login";
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



//@GetMapping("/login/oauth2/code/google")
//    public String oauth2LoginCallback(OAuth2AuthenticationToken authentication) {
//        OAuth2User principal = authentication.getPrincipal();
//        OAuth2UserInfo userInfo = new GoogleOAuth2UserInfo(principal.getAttributes()); // 구글 로그인 정보 처리
//
//        // 소셜 로그인 후 사용자 정보 처리
//        memberService.processOAuth2User(userInfo);
//       return "redirect:/home"; // 홈 페이지로 리다이렉트
}
