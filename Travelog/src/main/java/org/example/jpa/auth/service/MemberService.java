package org.example.jpa.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.jpa.auth.dto.MemberLoginReq;
import org.example.jpa.auth.dto.MemberRes;
import org.example.jpa.auth.dto.MemberSaveReq;
import org.example.jpa.auth.entity.Member;
import org.example.jpa.auth.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /** 🧩 아이디 중복 검사 */
    public boolean existsByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId).isPresent();
    }

    /** 🧩 닉네임 중복 검사 */
    public boolean existsByNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    /** 🧩 회원가입(join) */
    public void join(MemberSaveReq req) {
        System.out.println("💡 join 요청 값: loginId=" + req.getLoginId()
                + ", email=" + req.getEmail()
                + ", nickname=" + req.getNickname());

        if (memberRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("이미 존재하는 이메일입니다");
        }

        // ✅ 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(req.getPassword());
        System.out.println("암호화된 비밀번호: " + encodedPassword);

        Member member = Member.builder()
                .loginId(req.getLoginId())        // 로그인 아이디
                .email(req.getEmail())            // 이메일
                .password(encodedPassword)        // 비밀번호 암호화 저장
                .nickname(req.getNickname())      // 닉네임
                .role("USER")                     // 기본 권한
                .build();

        // DB에 저장
        memberRepository.save(member);
        System.out.println("회원가입 완료: " + member.getEmail());
    }

    /** 🧩 로그인 */
    public MemberRes login(MemberLoginReq req) {
        Member member = memberRepository.findByLoginId(req.getLoginId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 아이디입니다."));

        if (!passwordEncoder.matches(req.getPassword(), member.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return MemberRes.builder()
                .memberId(member.getMemberId())
                .loginId(member.getLoginId())
                .nickname(member.getNickname())
                .role(member.getRole())
                .build();
    }
}

//    // processOAuth2User 메서드 추가: 소셜 로그인 후 사용자 정보 처리 (회원 생성 또는 업데이트)
//    public Member processOAuth2User(OAuth2UserInfo userInfo) {
//        // 사용자의 이메일로 기존 회원 조회
//        Optional<Member> memberOpt = memberRepository.findByEmail(userInfo.getEmail());
//
//        Member member = memberOpt.orElseGet(() -> {
//            // 새 회원 생성
//            Member newMember = mapStruct.toEntity(userInfo);
//            memberRepository.save(newMember);  // 새 사용자 저장
//            return newMember;
//        });
//
//        // 기존 사용자 정보 업데이트
//        if (memberOpt.isPresent()) {
//            mapStruct.updateMemberFromUserInfo(userInfo, member);
//            memberRepository.save(member);  // 기존 사용자 정보 업데이트
//        }
//
//        return member;  // 처리된 회원 반환
//    }

