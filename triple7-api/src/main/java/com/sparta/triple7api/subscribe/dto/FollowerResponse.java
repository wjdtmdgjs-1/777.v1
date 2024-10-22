package com.sparta.triple7api.subscribe.dto;

import lombok.Getter;

@Getter
public class FollowerResponse {
    private final String FollowingUserName;
    private final String cryptoSymbol;

    public FollowerResponse(String FollowingUserName, String cryptoSymbol) {
        this.FollowingUserName = FollowingUserName;
        this.cryptoSymbol = cryptoSymbol;
    }
}
