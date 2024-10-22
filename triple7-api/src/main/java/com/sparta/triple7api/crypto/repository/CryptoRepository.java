package com.sparta.triple7api.crypto.repository;

import com.sparta.triple7api.crypto.entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {

    List<Crypto> findAll();
}
