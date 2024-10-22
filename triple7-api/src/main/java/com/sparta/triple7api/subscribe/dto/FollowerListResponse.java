package com.sparta.triple7api.subscribe.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class FollowerListResponse {
    private final List<FollowerResponse> subscriptions;

    public FollowerListResponse(List<FollowerResponse> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
