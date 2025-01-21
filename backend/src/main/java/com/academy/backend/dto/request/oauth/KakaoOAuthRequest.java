package com.academy.backend.dto.request.oauth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoOAuthRequest {
    private String authorizationCode;
}
