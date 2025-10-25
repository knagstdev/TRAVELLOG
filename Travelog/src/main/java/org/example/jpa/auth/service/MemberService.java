package org.example.jpa.auth.service;

import org.example.common.MapStruct;
import org.example.jpa.auth.entity.Member;
import org.example.jpa.auth.repository.MemberRepository;
import org.example.jpa.auth.service.oauth.OAuth2UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MapStruct mapStruct;  // MapStruct 자동 주입 (Spring Bean으로 주입)

    // 사용자가 존재하지 않으면 새로 생성하고, 있으면 업데이트
    public Member findOrCreateUser(OAuth2UserInfo userInfo) {
        String email = userInfo.getEmail();

        // 이메일로 사용자 검색 (Optional<Member>로 반환)
        Optional<Member> memberOpt = memberRepository.findByEmail(email);

        // 이메일로 사용자가 없으면 새로 생성
        Member member = memberOpt.orElseGet(() -> {
            // 새 회원 생성
            Member newMember = mapStruct.toEntity(userInfo);  // MapStruct로 회원 생성
            memberRepository.save(newMember);  // 새 사용자 저장
            return newMember;
        });

        // 기존 사용자가 있으면 정보 업데이트
        if (memberOpt.isPresent()) {
            mapStruct.updateMemberFromUserInfo(userInfo, member);  // MapStruct로 회원 업데이트
            memberRepository.save(member);  // 기존 사용자 정보 업데이트
        }

        return member;
    }

    // processOAuth2User 메서드 추가: 소셜 로그인 후 사용자 정보 처리 (회원 생성 또는 업데이트)
    public Member processOAuth2User(OAuth2UserInfo userInfo) {
        // 사용자의 이메일로 기존 회원 조회
        Optional<Member> memberOpt = memberRepository.findByEmail(userInfo.getEmail());

        Member member = memberOpt.orElseGet(() -> {
            // 새 회원 생성
            Member newMember = mapStruct.toEntity(userInfo);
            memberRepository.save(newMember);  // 새 사용자 저장
            return newMember;
        });

        // 기존 사용자 정보 업데이트
        if (memberOpt.isPresent()) {
            mapStruct.updateMemberFromUserInfo(userInfo, member);
            memberRepository.save(member);  // 기존 사용자 정보 업데이트
        }

        return member;  // 처리된 회원 반환
    }
}
