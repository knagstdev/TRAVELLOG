package org.example.common;

import org.example.jpa.auth.entity.Member;
import org.example.jpa.auth.service.oauth.OAuth2UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapStruct {

    // OAuth2UserInfo에서 Member 엔티티로 변환
    @Mapping(target = "email", source = "email")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "profileImage", source = "profileImage")
    @Mapping(target = "provider", source = "provider")
    @Mapping(target = "providerId", source = "providerId")
    @Mapping(target = "role", constant = "ROLE_USER")  // 기본 역할 설정
    Member toEntity(OAuth2UserInfo userInfo);

    // 기존 Member 업데이트 메서드 (void -> Member 반환으로 변경)
    @Mapping(target = "email", source = "userInfo.email")
    @Mapping(target = "name", source = "userInfo.name")
    @Mapping(target = "profileImage", source = "userInfo.profileImage")
    @Mapping(target = "provider", source = "userInfo.provider")
    @Mapping(target = "providerId", source = "userInfo.providerId")
    Member updateMemberFromUserInfo(OAuth2UserInfo userInfo, Member member);  // 반환 타입을 void에서 Member로 변경
}
