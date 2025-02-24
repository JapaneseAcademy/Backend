package com.academy.backend.auth.dto.response;

import com.academy.backend.member.domain.Member;
import com.academy.backend.member.domain.Role;
import com.academy.backend.auth.dto.jwt.AuthToken;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {

    private Long id;
    private String loginId;
    private String name;
    private Role role;
    private boolean isActive;
    private AuthToken token;
    private boolean requiresSignUp;

    public LoginResponse(Member member, AuthToken token, boolean requiresSignUp) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.role = member.getRole();
        this.isActive = member.getIsActive();
        this.token = token;
        this.requiresSignUp = requiresSignUp;
    }

    public LoginResponse(String loginId, boolean requiresSignUp) {
        this.loginId = loginId;
        this.requiresSignUp = requiresSignUp;
    }
}
