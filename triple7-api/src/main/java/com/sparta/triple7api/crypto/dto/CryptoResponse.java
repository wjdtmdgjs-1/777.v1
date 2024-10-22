package com.sparta.triple7api.crypto.dto;

import com.sparta.triple7api.crypto.entity.Crypto;
import lombok.Getter;

@Getter
public class CryptoResponse {
    private Long id;
    private String symbol;
    private String description;

    public CryptoResponse(Crypto crypto) {
        this.id = crypto.getId();
        this.symbol = crypto.getSymbol();
        this.description = crypto.getDescription();
    }
}
