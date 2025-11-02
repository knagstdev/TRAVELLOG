//package org.example.jpa.auth.service.oauth;
//
//import java.util.Map;
//
///**
// * 🟤 카카오 로그인 사용자 정보 파싱 클래스
// */
//public class KakaoOAuth2UserInfo implements OAuth2UserInfo {
//
//    private final Map<String, Object> attributes;
//
//    // ✅ 생성자 추가 (현재 오류 해결 핵심)
//    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
//        this.attributes = attributes;
//    }
//
//    @Override
//    public String getProviderId() {
//        return String.valueOf(attributes.get("id")); // id는 Long이라 문자열 변환
//    }
//
//    @Override
//    public String getProvider() {
//        return "kakao";
//    }
//
//    @Override
//    public String getEmail() {
//        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
//        return kakaoAccount != null ? (String) kakaoAccount.get("email") : null;
//    }
//
//    @Override
//    public String getName() {
//        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
//        if (kakaoAccount != null) {
//            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
//            if (profile != null) return (String) profile.get("nickname");
//        }
//        return null;
//    }
//
//    @Override
//    public String getProfileImage() {
//        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
//        if (kakaoAccount != null) {
//            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
//            if (profile != null) return (String) profile.get("profile_image_url");
//        }
//        return null;
//    }
//}
