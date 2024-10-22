package com.sparta.triple7api.crypto.service;

import com.sparta.triple7api.crypto.dto.CryptoResponse;
import com.sparta.triple7api.crypto.entity.Crypto;
import com.sparta.triple7api.crypto.repository.CryptoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CryptoService {

    private final CryptoRepository cryptoRepository;

    public CryptoResponse getCryptoInfo(Long cryptoId) {
        Crypto crypto = this.cryptoRepository.findById(cryptoId).orElseThrow();
        return new CryptoResponse(crypto);
    }
}
