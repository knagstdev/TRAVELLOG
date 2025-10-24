package org.example.common;

import org.mapstruct.*;
import org.example.jpa.auth.entity.Member;
import org.example.jpa.auth.service.oauth.OAuth2UserInfo;

/**
 * 🔐 로그인 / 회원가입 / 소셜로그인 전용 통합 MapStruct 매퍼
 * - 회원가입 DTO → Member 엔티티
 * - Member 엔티티 → 세션/상세 DTO
 * - OAuth2UserInfo → Member (소셜 로그인 시)
 */
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MapStruct {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", constant = "ROLE_USER")
    Member toEntity(OAuth2UserInfo userInfo);

    // ✅ 기존 Member 갱신용
    void updateMemberFromUserInfo(OAuth2UserInfo userInfo, @MappingTarget Member member);
    }


