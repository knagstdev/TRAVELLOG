package org.example.jpa.auth.repository;

import org.example.jpa.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일로 사용자를 Optional로 조회
    Optional<Member> findByEmail(String email);  // Optional<Member>로 반환하도록 수정
}
