package com.sparta.triple7api.crypto.repository;

import com.sparta.triple7api.crypto.entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {
}
