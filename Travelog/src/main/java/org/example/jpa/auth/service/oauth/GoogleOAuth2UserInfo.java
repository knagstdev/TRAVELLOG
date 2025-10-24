package org.example.jpa.auth.service.oauth;

import java.util.Map;

/**
 * 🔵 구글 로그인 사용자 정보 파싱 클래스
 */
public class GoogleOAuth2UserInfo implements OAuth2UserInfo {

    private final Map<String, Object> attributes;

    // ✅ 생성자 (현재 CustomOAuth2UserService에서 호출하는 형태)
    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub"); // 구글 고유 ID
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getProfileImage() {
        return (String) attributes.get("picture");
    }
}
