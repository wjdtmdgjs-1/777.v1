package com.sparta.triple7api.subscribe.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class FollowingListResponse {
    private final List<FollowingResponse> subscriptions;

    public FollowingListResponse(List<FollowingResponse> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
