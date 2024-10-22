package com.sparta.triple7api.trade.service;

import com.sparta.triple7api.common.dto.AuthUser;
import com.sparta.triple7api.trade.dto.request.TradeRequestDto;
import com.sparta.triple7api.trade.dto.response.TradeResponseDto;
import com.sparta.triple7api.trade.repository.TradeRepository;
import com.sparta.triple7api.user.entity.User;
import com.sparta.triple7api.user.repository.UserRepository;
import com.sparta.triple7api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeService {
    private final TradeRepository tradeRepository;
    private final UserRepository userRepository;
//    private final CryptoRepository cryptoRepository;

//    public TradeResponseDto postTrade(AuthUser authUser, long cryptoId, TradeRequestDto tradeRequestDto) {
//        User user = userRepository.findById(authUser.getId()).orElseThrow(()->new NullPointerException("no such user"));
//        Crypto crypto = CryptoRepository.findById(cryptoId).orElseThrow(()->new NullPointerException("no such user"));
//        user.
//    }
}
