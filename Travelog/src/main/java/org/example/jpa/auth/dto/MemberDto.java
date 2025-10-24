package org.example.jpa.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private Long id;
    private String email;
    private String name;
    private String profileImage;
    private String provider;
    private String role;
}
