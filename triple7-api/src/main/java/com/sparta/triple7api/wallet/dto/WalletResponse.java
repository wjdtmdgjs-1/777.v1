package com.sparta.triple7api.wallet.dto;

import com.sparta.triple7api.wallet.entity.Wallet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WalletResponse {

    private final Long id;
    private final Long amount;
    private final String cryptoSymbol;
    private final String userEmail;

    public WalletResponse(Wallet wallet) {
        this.id = wallet.getId();
        this.amount = wallet.getAmount();
        this.cryptoSymbol = wallet.getCryptoSymbol();
        this.userEmail = wallet.getUser().getEmail();
    }
}
