package com.sparta.triple7api.subscribe.entity;

import com.sparta.triple7api.common.entity.Timestamped;
import com.sparta.triple7api.crypto.entity.Crypto;
import com.sparta.triple7api.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "following")
@NoArgsConstructor
public class Subscriptions extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_user_id") // 구독할 사람 Id
    private User followingUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_user_id") // 내 Id
    private User followerUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crypto_id")
    private Crypto crypto;

    @Column(name = "crypto_amount")
    private Double cryptoAmount;

    public static Subscriptions of(User followingUser, User followerUser, Crypto crypto, Double cryptoAmount) {
        Subscriptions subscriptions = new Subscriptions();
        subscriptions.followingUser = followingUser;
        subscriptions.followerUser = followerUser;
        subscriptions.crypto = crypto;
        subscriptions.cryptoAmount = cryptoAmount;
        return subscriptions;
    }
}
