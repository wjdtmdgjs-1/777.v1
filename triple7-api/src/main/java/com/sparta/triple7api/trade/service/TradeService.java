package com.sparta.triple7api.trade.service;

import com.sparta.triple7api.common.dto.AuthUser;
import com.sparta.triple7api.common.exception.InvalidRequestException;
import com.sparta.triple7api.crypto.entity.Crypto;
import com.sparta.triple7api.crypto.repository.CryptoRepository;
import com.sparta.triple7api.subscribe.entity.Subscriptions;
import com.sparta.triple7api.subscribe.repository.SubscriptionsRepository;
import com.sparta.triple7api.trade.dto.request.TradeRequestDto;
import com.sparta.triple7api.trade.dto.response.TradeResponseDto;
import com.sparta.triple7api.trade.entity.Trade;
import com.sparta.triple7api.trade.enums.TradeFor;
import com.sparta.triple7api.trade.enums.TradeType;
import com.sparta.triple7api.trade.repository.TradeRepository;
import com.sparta.triple7api.user.entity.User;
import com.sparta.triple7api.user.repository.UserRepository;
import com.sparta.triple7api.wallet.entity.Wallet;
import com.sparta.triple7api.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TradeService {
    private final TradeRepository tradeRepository;
    private final UserRepository userRepository;
    private final CryptoRepository cryptoRepository;
    private final WalletRepository walletRepository;
    private final SubscriptionsRepository subscriptionsRepository;
    private final RedisTemplate<String, Long> redisTemplate;

    @Transactional
    public TradeResponseDto postTrade(AuthUser authUser, long cryptoId, TradeRequestDto tradeRequestDto) {
        User user = userRepository.findById(authUser.getId()).orElseThrow(()->new NullPointerException("no such user"));
        Crypto crypto = cryptoRepository.findById(cryptoId).orElseThrow(()->new NullPointerException("no such crypto"));

        Long price = Optional.ofNullable(redisTemplate.opsForValue().get(crypto.getSymbol())).orElse(0L);

        Wallet wallet = walletRepository.findByUserIdAndCryptoSymbol(user.getId(),crypto.getSymbol());
        if(tradeRequestDto.getTradeType().equals(TradeType.Authority.BUY)){
            if(wallet.getCash() < price*tradeRequestDto.getAmount()) {
                throw new InvalidRequestException("no such money");
            }
            wallet.update(wallet.getAmount() + tradeRequestDto.getAmount(),
                    wallet.getCash() - (long)(price * tradeRequestDto.getAmount()));
        }else if(tradeRequestDto.getTradeType().equals(TradeType.Authority.SELL)){
            if(wallet.getAmount() < tradeRequestDto.getAmount()){
                throw new InvalidRequestException("no such amount");
            }
            wallet.update((wallet.getAmount() - tradeRequestDto.getAmount()),
                    wallet.getCash() + (long)(price * tradeRequestDto.getAmount()));
        }
        Trade trade = new Trade(user,crypto,tradeRequestDto.getTradeType(),tradeRequestDto.getTradeFor(),tradeRequestDto.getAmount(),price,(long)(price * tradeRequestDto.getAmount()),user.getId());
        tradeRepository.save(trade);
        return new TradeResponseDto(crypto.getSymbol(),tradeRequestDto.getAmount(),tradeRequestDto.getTradeType(),(long)(price * tradeRequestDto.getAmount()));
    }

    @Transactional
    public TradeResponseDto postSubscriptionsTrade(AuthUser authUser, long cryptoId, long subscritionsId, TradeRequestDto tradeRequestDto) {

        //authuser user subscription followinguser 일치하는지확인
        User user = userRepository.findById(authUser.getId())
                .orElseThrow(() -> new InvalidRequestException("no such user"));

        Crypto crypto = cryptoRepository.findById(cryptoId)
                .orElseThrow(()->new NullPointerException("no such crypto"));

        Subscriptions subscriptions = subscriptionsRepository.findById(subscritionsId)
                .orElseThrow(() -> new InvalidRequestException("no such subscriptions"));

        if (!subscriptions.getFollowingUser().getId().equals(user.getId())) {
            throw new InvalidRequestException("different user");
        }
        if(!subscriptions.getCrypto().getId().equals(cryptoId)){
            throw new InvalidRequestException("different crypto");
        }

        //subscription 찾아와서 코인 종류 뽑고 tradeRequest에서 코인갯수체크(갯수보다많으면 throw)/ type=sell로 통일 으로
        Long price = Optional.ofNullable(redisTemplate.opsForValue().get(crypto.getSymbol())).orElse(0L);
        Long totalPrice = (long)(price * tradeRequestDto.getAmount());

        Wallet userWallet = walletRepository.findByUserIdAndCryptoSymbol(user.getId(),crypto.getSymbol());
        Wallet followerWallet = walletRepository.findByUserIdAndCryptoSymbol(subscriptions.getFollowerUser().getId(),crypto.getSymbol());

        if (tradeRequestDto.getAmount().equals(subscriptions.getCryptoAmount())) {
            Trade trade = new Trade(user,crypto,TradeType.Authority.SELL, TradeFor.Authority.OTHER, subscriptions.getCryptoAmount(),price,(long)(price * tradeRequestDto.getAmount()),subscriptions.getFollowerUser().getId());
            userWallet.updateCash(totalPrice*0.1);
            followerWallet.updateCash(totalPrice*0.9);
            tradeRepository.save(trade);
            subscriptionsRepository.delete(subscriptions);
        }
        else {
            throw new InvalidRequestException("write same amount");
        }

        return new TradeResponseDto(crypto.getSymbol(),tradeRequestDto.getAmount(),TradeType.Authority.SELL,price);
    }

    public List<TradeResponseDto> getTradeList(AuthUser authUser, long cryptoId) {
        User user = userRepository.findById(authUser.getId()).orElseThrow(()->new NullPointerException("no such user"));
        Crypto crypto = cryptoRepository.findById(cryptoId).orElseThrow(()->new NullPointerException("no such crypto"));
        List<Trade> tradeList = tradeRepository.findAllByCryptoAndUser(crypto,user);

        return tradeList.stream().map(Trade->new TradeResponseDto(Trade.getCrypto().getSymbol(),Trade.getAmount(),String.valueOf(Trade.getTradeType()),Trade.getPrice())).toList();
    }


    public List<TradeResponseDto> getAllTradeList(AuthUser authUser) {
        User user = userRepository.findById(authUser.getId()).orElseThrow(()->new NullPointerException("no such user"));
        List<Trade> tradeList = tradeRepository.findAllByUser(user);

        return tradeList.stream().map(Trade->new TradeResponseDto(Trade.getCrypto().getSymbol(),Trade.getAmount(),String.valueOf(Trade.getTradeType()),Trade.getPrice())).toList();
    }
}
