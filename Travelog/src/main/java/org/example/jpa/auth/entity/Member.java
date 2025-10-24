package org.example.jpa.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TB_MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String profileImage;

    // 소셜 로그인 제공자 (google/naver/kakao)
    private String provider;

    // provider 내부 id (ex: kakao id)
    private String providerId;

    // 기본 사용자 역할
    private String role;
}
