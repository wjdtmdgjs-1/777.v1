package com.sparta.triple7api.subscribe.controller;

import com.sparta.triple7api.common.dto.AuthUser;
import com.sparta.triple7api.subscribe.dto.FollowerListResponse;
import com.sparta.triple7api.subscribe.dto.FollowingListResponse;
import com.sparta.triple7api.subscribe.dto.FollowingRequest;
import com.sparta.triple7api.subscribe.dto.FollowingResponse;
import com.sparta.triple7api.subscribe.service.SubscriptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionsController {

    private final SubscriptionsService subscriptionsService;

    @PostMapping
    public ResponseEntity<FollowingResponse> subscribe(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody FollowingRequest followingRequest) {
        return ResponseEntity.ok(subscriptionsService.subscribe(authUser, followingRequest));
    }


    @GetMapping("/following")
    public ResponseEntity<FollowingListResponse> getFollowing(
            @AuthenticationPrincipal AuthUser authUser) {
        return ResponseEntity.ok(subscriptionsService.getFollowing(authUser));
    }

    @GetMapping("/follower")
    public ResponseEntity<FollowerListResponse> getFollower(
            @AuthenticationPrincipal AuthUser authUser) {
        return ResponseEntity.ok(subscriptionsService.getFollower(authUser));
    }
}
