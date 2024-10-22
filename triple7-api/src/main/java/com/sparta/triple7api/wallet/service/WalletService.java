package com.sparta.triple7api.wallet.service;

import com.sparta.triple7api.common.dto.AuthUser;
import com.sparta.triple7api.wallet.dto.WalletResponse;
import com.sparta.triple7api.wallet.entity.Wallet;
import com.sparta.triple7api.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public List<WalletResponse> getWallets(AuthUser authUser) {
        List<Wallet> wallets = walletRepository.findAllByUserId(authUser.getId());
        if (wallets.isEmpty()) {
            throw new IllegalArgumentException("해당 유저의 지갑을 찾을 수 없습니다.");
        }
        return wallets.stream()
                .map(WalletResponse::new)
                .collect(Collectors.toList());
    }


}
