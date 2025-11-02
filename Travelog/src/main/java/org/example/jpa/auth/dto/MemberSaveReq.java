package org.example.jpa.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSaveReq {
    private String loginId;
    private String email;
    private String password;
    private String nickname;
}
