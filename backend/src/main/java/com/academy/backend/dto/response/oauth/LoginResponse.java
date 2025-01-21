package com.academy.backend.dto.response.oauth;

import com.academy.backend.domain.member.Role;
import com.academy.backend.dto.jwt.AuthToken;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {

    private Long id;
    private String name;
    private Role role;
    private boolean isActive;
    private AuthToken token;

    public LoginResponse(Long id, String name, Role role, boolean isActive, AuthToken token) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.isActive = isActive;
        this.token = token;
    }
}
