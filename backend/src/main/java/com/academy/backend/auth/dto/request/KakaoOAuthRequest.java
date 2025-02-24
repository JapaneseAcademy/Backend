package com.academy.backend.auth.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoOAuthRequest {
    private String authorizationCode;
}
