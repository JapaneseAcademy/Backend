package com.academy.backend.service.oauth;

import com.academy.backend.config.auth.AuthTokenGenerator;
import com.academy.backend.domain.member.Member;
import com.academy.backend.dto.jwt.AuthToken;
import com.academy.backend.dto.response.oauth.KakaoOAuthTokenResponse;
import com.academy.backend.dto.response.oauth.LoginResponse;
import com.academy.backend.exception.member.UserNotRegisteredException;
import com.academy.backend.exception.oauth.KakaoTokenResponseException;
import com.academy.backend.repository.MemberRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class KakaoOAuthServiceImpl implements KakaoOAuthService{

    private static final String provider = "kakao_";

    @Value("${oauth.kakao.rest-api-key}")
    private String restApiKey;

    @Value("${oauth.kakao.redirect-uri}")
    private String redirectUri;

    @Value("${oauth.kakao.client-secret}")
    private String clientSecret;

    private final MemberRepository memberRepository;
    private final AuthTokenGenerator authTokenGenerator;

    public LoginResponse kakaoLogin(String authorizationCode) {
        String accessToken = getAccessToken(authorizationCode);
        Long userId = getKakaoId(accessToken);
        return login(userId);
    }

    public String getAccessToken(String authorizationCode) {
        WebClient webClient = WebClient.builder().build();

        KakaoOAuthTokenResponse tokenResponse = webClient.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .body(BodyInserters.fromFormData("grant_type", "authorization_code")
                        .with("client_id", restApiKey)
                        .with("redirect_uri", redirectUri)
                        .with("code", authorizationCode)
                        .with("client_secret", clientSecret))
                .retrieve()
                .bodyToMono(KakaoOAuthTokenResponse.class)
                .block();

        if (tokenResponse == null) {
            throw new KakaoTokenResponseException();
        }

        return tokenResponse.getAccessToken();
    }

    public Long getKakaoId(String accessToken) {
        KakaoUser kakaoUser = WebClient.create()
                .get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(KakaoUser.class)
                .block();

        System.out.println("kakaoUser.getId() = " + kakaoUser.getId());
        return kakaoUser.getId();
    }

    public LoginResponse login(Long userId) {
        String loginId = provider + userId;
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(
                () -> new UserNotRegisteredException()
        );
        AuthToken token = authTokenGenerator.generate(loginId);

        return new LoginResponse(member.getId(), member.getName(), member.getRole(), member.isActive(), token);
    }

    @Getter
    @Setter
    private static class KakaoUser {
        @JsonProperty("id")
        private Long id;
    }
}
