package com.academy.backend.dto.response.oauth;

import com.academy.backend.domain.member.Member;
import com.academy.backend.domain.member.Role;
import com.academy.backend.dto.jwt.AuthToken;
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
        this.isActive = member.isActive();
        this.token = token;
        this.requiresSignUp = requiresSignUp;
    }

    public LoginResponse(String loginId, boolean requiresSignUp) {
        this.loginId = loginId;
        this.requiresSignUp = requiresSignUp;
    }

    public LoginResponse(Long id, String name, Role role, boolean isActive, AuthToken token) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.isActive = isActive;
        this.token = token;
    }
}
