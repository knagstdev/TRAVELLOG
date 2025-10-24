package org.example.jpa.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.common.MapStruct;
import org.example.jpa.auth.entity.Member;
import org.example.jpa.auth.repository.MemberRepository;
import org.example.jpa.auth.service.oauth.*;
import org.example.jpa.auth.security.CustomOAuth2User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final MapStruct mapStruct; // ✅ 자동 주입 (componentModel="spring")

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        OAuth2UserInfo userInfo = switch (registrationId) {
            case "naver" -> new NaverOAuth2UserInfo((Map<String, Object>) attributes.get("response"));
            case "kakao" -> new KakaoOAuth2UserInfo((Map<String, Object>) attributes);
            default -> new GoogleOAuth2UserInfo(attributes);
        };

        // ✅ 존재 여부 확인 후 MapStruct로 처리
        Member member = memberRepository.findByEmail(userInfo.getEmail())
                .map(existing -> {
                    mapStruct.updateMemberFromUserInfo(userInfo, existing);
                    return existing;
                })
                .orElseGet(() -> mapStruct.toEntity(userInfo));

        // ✅ DB 저장
        memberRepository.save(member);

        return new CustomOAuth2User(member, attributes);
    }
}
