package com.sparta.triple7api.auth.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignupResponse {

    private final String bearerToken;

    public static SignupResponse of(String bearerToken) {
        return new SignupResponse(bearerToken);
    }
}
