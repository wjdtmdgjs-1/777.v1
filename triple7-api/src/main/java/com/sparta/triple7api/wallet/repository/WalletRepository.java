package com.sparta.triple7api.wallet.repository;

import com.sparta.triple7api.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    List<Wallet> findAllByUserId(Long userId);
    Wallet findByUserIdAndCryptoSymbol(Long id, String symbol);
}
