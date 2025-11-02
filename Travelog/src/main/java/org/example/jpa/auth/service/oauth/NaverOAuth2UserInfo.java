//package org.example.jpa.auth.service.oauth;
//
//import java.util.Map;
//
///**
// * 🇰🇷 네이버 로그인 사용자 정보 파싱 클래스
// */
//public class NaverOAuth2UserInfo implements OAuth2UserInfo {
//
//    private final Map<String, Object> attributes;
//
//    // ✅ 생성자 추가 (현재 오류 해결 핵심)
//    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
//        this.attributes = attributes;
//    }
//
//    @Override
//    public String getProviderId() {
//        return (String) attributes.get("id");
//    }
//
//    @Override
//    public String getProvider() {
//        return "naver";
//    }
//
//    @Override
//    public String getEmail() {
//        return (String) attributes.get("email");
//    }
//
//    @Override
//    public String getName() {
//        return (String) attributes.get("name");
//    }
//
//    @Override
//    public String getProfileImage() {
//        return (String) attributes.get("profile_image");
//    }
//}
