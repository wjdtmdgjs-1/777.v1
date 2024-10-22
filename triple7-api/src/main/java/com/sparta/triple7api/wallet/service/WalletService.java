package com.sparta.triple7api.wallet.service;

import com.sparta.triple7api.common.dto.AuthUser;
import com.sparta.triple7api.crypto.entity.Crypto;
import com.sparta.triple7api.crypto.repository.CryptoRepository;
import com.sparta.triple7api.user.entity.User;
import com.sparta.triple7api.wallet.dto.WalletResponse;
import com.sparta.triple7api.wallet.entity.Wallet;
import com.sparta.triple7api.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final CryptoRepository cryptoRepository;

    public List<WalletResponse> getWallets(AuthUser authUser) {
        List<Wallet> wallets = walletRepository.findAllByUserId(authUser.getId());
        if (wallets.isEmpty()) {
            throw new IllegalArgumentException("해당 유저의 지갑을 찾을 수 없습니다.");
        }
        return wallets.stream()
                .map(WalletResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createWallet(User user) {
        List<Crypto> cryptos = cryptoRepository.findAll();  // 데이터베이스에서 모든 코인을 조회

        // 각 코인에 대한 지갑 생성
        for (Crypto crypto : cryptos) {
            Wallet wallet = new Wallet(user, 0L, crypto.getSymbol(),1000000000L);
            walletRepository.save(wallet);
        }
    }
}
