package com.sparta.triple7api.trade.entity;

import com.sparta.triple7api.crypto.entity.Crypto;
import com.sparta.triple7api.trade.enums.TradeFor;
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

    @Enumerated
    private TradeFor tradeFor;

    @Column(name = "amount")
    private Double amount;
    @Column(name = "price")
    private Long price;
    @Column(name = "totalPrice")
    private Long totalPrice;
    @Column(name = "moneyFrom")
    private Long moneyFrom;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "coin_id")
    private Crypto crypto;

    public Trade(User user, Crypto crypto, String tradeType,String tradeFor,Double amount, Long price,Long totalPrice,Long moneyFrom) {
        this.user=user;
        this.crypto=crypto;
        this.tradeType = TradeType.valueOf(tradeType);
        this.tradeFor = TradeFor.valueOf(tradeFor);
        this.amount=amount;
        this.price=price;
        this.totalPrice=totalPrice;
        this.moneyFrom = moneyFrom;
    }

}
