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
    private final MapStruct mapStruct; // ✅ MapStruct 자동 주입 (componentModel="spring")

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 소셜 로그인 제공자에 따른 OAuth2UserInfo 생성
        OAuth2UserInfo userInfo = switch (registrationId) {
            case "naver" -> new NaverOAuth2UserInfo((Map<String, Object>) attributes.get("response"));
            case "kakao" -> new KakaoOAuth2UserInfo((Map<String, Object>) attributes);
            default -> new GoogleOAuth2UserInfo(attributes);
        };

        // ✅ 존재 여부 확인 후 MapStruct로 처리
        Member member = memberRepository.findByEmail(userInfo.getEmail())
                .map(existing -> {
                    // 기존 사용자가 있으면 업데이트
                    mapStruct.updateMemberFromUserInfo(userInfo, existing);
                    return existing;
                })
                .orElseGet(() -> {
                    // 새로운 사용자는 새로 생성
                    Member newMember = mapStruct.toEntity(userInfo);
                    return newMember;
                });

        // ✅ DB 저장
        memberRepository.save(member);

        // 사용자 정보를 CustomOAuth2User로 감싸서 반환
        return new CustomOAuth2User(member, attributes);
    }
}