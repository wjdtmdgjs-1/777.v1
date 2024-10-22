package com.sparta.triple7api.trade.service;

import com.sparta.triple7api.common.dto.AuthUser;
import com.sparta.triple7api.common.exception.InvalidRequestException;
import com.sparta.triple7api.crypto.entity.Crypto;
import com.sparta.triple7api.crypto.repository.CryptoRepository;
import com.sparta.triple7api.trade.dto.request.TradeRequestDto;
import com.sparta.triple7api.trade.dto.response.TradeResponseDto;
import com.sparta.triple7api.trade.entity.Trade;
import com.sparta.triple7api.trade.enums.TradeType;
import com.sparta.triple7api.trade.repository.TradeRepository;
import com.sparta.triple7api.user.entity.User;
import com.sparta.triple7api.user.repository.UserRepository;
import com.sparta.triple7api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TradeService {
    private final TradeRepository tradeRepository;
    private final UserRepository userRepository;
    private final CryptoRepository cryptoRepository;
    private final RedisTemplate<String, Long> redisTemplate;

    public TradeResponseDto postTrade(AuthUser authUser, long cryptoId, TradeRequestDto tradeRequestDto) {
        User user = userRepository.findById(authUser.getId()).orElseThrow(()->new NullPointerException("no such user"));
        Crypto crypto = cryptoRepository.findById(cryptoId).orElseThrow(()->new NullPointerException("no such crypto"));

        Long price = Optional.ofNullable(redisTemplate.opsForValue().get(crypto.getSymbol())).orElse(0L);

        Wallet wallet = walletRepository.findByUserIDAndCryptoSymbol(user.getId(),crypto.getSymbol());
        if(tradeRequestDto.getTradeType().equals(TradeType.Authority.BUY)){
            if(wallet.getCash < price*tradeRequestDto.getAmount()) {
                throw new InvalidRequestException("no such money");
            }
            wallet.update(wallet.getAmount + tradeRequestDto.getAmount(),wallet.getCash(),
                    wallet.getCash() - price * tradeRequestDto.getAmount());
        }else if(tradeRequestDto.getTradeType().equals(TradeType.Authority.SELL)){
            if(wallet.getAmount < tradeRequestDto.getAmount()){
                throw new InvalidRequestException("no such amount");
            }
            wallet.update((wallet.getAmount - tradeRequestDto.getAmount()),
                    wallet.getCash() + (price * tradeRequestDto.getAmount()));
        }
        Trade trade = new Trade(user,crypto,tradeRequestDto.getTradeType(),tradeRequestDto.getAmount(),price,price * tradeRequestDto.getAmount());
        tradeRepository.save(trade);
        return new TradeResponseDto(crypto.getSymbol(),tradeRequestDto.getAmount(),tradeRequestDto.getTradeType(),price * tradeRequestDto.getAmount());
    }
}
