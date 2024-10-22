package com.sparta.triple7api.trade.repository;

import com.sparta.triple7api.crypto.entity.Crypto;
import com.sparta.triple7api.trade.entity.Trade;
import com.sparta.triple7api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade,Long> {
    List<Trade> findAllByCryptoAndUser(Crypto crypto, User user);

    List<Trade> findAllByUser(User user);
}
