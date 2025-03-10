package com.academy.backend.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequest {

    @NotNull(message = "사용자의 loginId는 필수 입력 사항입니다.")
    private String loginId;
}
