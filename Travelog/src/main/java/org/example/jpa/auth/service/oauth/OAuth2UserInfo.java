package org.example.jpa.auth.service.oauth;

import java.util.Map;

/**
 * 🌐 OAuth2UserInfo (소셜 로그인 공통 인터페이스)
 * - 구글, 네이버, 카카오 로그인 사용자 정보를 통합 규격으로 정의
 */
public interface OAuth2UserInfo {
    String getProviderId();    // 소셜 플랫폼 고유 ID
    String getProvider();      // "google" / "naver" / "kakao"
    String getEmail();
    String getName();
    String getProfileImage();
}
