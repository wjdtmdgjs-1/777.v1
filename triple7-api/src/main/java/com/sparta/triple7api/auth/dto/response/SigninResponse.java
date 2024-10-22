package com.sparta.triple7api.auth.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SigninResponse {

    private final String bearerToken;

    public static SigninResponse of(String bearerToken) {
        return new SigninResponse(bearerToken);
    }
}
