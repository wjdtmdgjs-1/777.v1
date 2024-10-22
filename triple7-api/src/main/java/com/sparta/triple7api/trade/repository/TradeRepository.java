package com.sparta.triple7api.trade.repository;

import com.sparta.triple7api.trade.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade,Long> {
}
