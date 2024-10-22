package com.sparta.triple7api.trade.entity;

import com.sparta.triple7api.trade.enums.TradeType;
import com.sparta.triple7api.common.entity.Timestamped;
import com.sparta.triple7api.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Trade extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private TradeType tradeType;

    private Long amount;

    private Long price;

    private Long totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "coin_id")
    private Crypto crypto;

    void setTotalPrice(){
        this.totalPrice = this.amount * this.price;
    }



}
