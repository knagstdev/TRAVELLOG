package org.example.jpa.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MEMBER")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEMBER_ID")
    @SequenceGenerator(name = "SEQ_MEMBER_ID", sequenceName = "SEQ_MEMBER_ID", allocationSize = 1)
    private Long memberId;
    private String loginId;
    private String email;
    private String password;
    private String nickname;
    private String profileUrl;
    private String role;
    private String socialId;
    private String socialType;
}
