package com.sparta.triple7api.subscribe.dto;

import lombok.Getter;

@Getter
public class FollowingRequest {
    private Long followingUserId;
    private Long cryptoId;
    private Double cryptoAmount;
    private Integer maxPercent;
}
