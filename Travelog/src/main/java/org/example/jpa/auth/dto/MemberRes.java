package org.example.jpa.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberRes {
    private Long memberId;
    private String loginId;
    private String nickname;
    private String email;
    private String role;
}
