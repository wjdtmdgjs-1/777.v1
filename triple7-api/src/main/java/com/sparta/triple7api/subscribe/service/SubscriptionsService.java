package com.sparta.triple7api.subscribe.service;

import com.sparta.triple7api.common.dto.AuthUser;
import com.sparta.triple7api.crypto.entity.Crypto;
import com.sparta.triple7api.crypto.repository.CryptoRepository;
import com.sparta.triple7api.subscribe.dto.*;
import com.sparta.triple7api.subscribe.entity.Following;
import com.sparta.triple7api.subscribe.repository.FollowingRepository;
import com.sparta.triple7api.user.entity.User;
import com.sparta.triple7api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscriptionsService {

    private final FollowingRepository followingRepository;
    private final UserRepository userRepository;
    private final CryptoRepository cryptoRepository;

    @Transactional
    public FollowingResponse subscribe(AuthUser authUser, FollowingRequest followingRequest) {

        Crypto crypto = cryptoRepository.findById(followingRequest.getCryptoId())
                .orElseThrow(() -> new IllegalArgumentException("없는 코인입니다."));

        User user = userRepository.findById(authUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("없는 유저입니다."));

        User followingUser = userRepository.findById(followingRequest.getFollowingUserId())
                .orElseThrow(() -> new IllegalArgumentException("없는 유저입니다."));

        Following following = Following.of(followingUser, user, crypto, followingRequest.getCryptoAmount());

        followingRepository.save(following);
        
        return new FollowingResponse(following.getFollowingUser().getName(), following.getCrypto().getSymbol());
    }

    public FollowingListResponse getFollowing(AuthUser authUser) {
        return new FollowingListResponse(
                followingRepository.findAllByFollowerUserId(authUser.getId())
                        .stream()
                        .map(f -> new FollowingResponse(f.getFollowingUser().getName(), f.getCrypto().getSymbol()))
                        .toList());
    }

    public FollowerListResponse getFollower(AuthUser authUser) {
        return new FollowerListResponse(
                followingRepository.findAllByFollowingUserId(authUser.getId())
                        .stream()
                        .map(f -> new FollowerResponse(f.getFollowerUser().getName(), f.getCrypto().getSymbol()))
                        .toList());
    }
}
