package com.sparta.triple7api.wallet.entity;

import com.sparta.triple7api.common.entity.Timestamped;
import com.sparta.triple7api.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "wallets")
public class Wallet extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ammount")
    private Long amount;

    @Column(name = "crypto_symbol")
    private String cryptoSymbol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
