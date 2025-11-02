package org.example.jpa.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginReq {
    private String loginId;
    private String email;
    private String password;
}
