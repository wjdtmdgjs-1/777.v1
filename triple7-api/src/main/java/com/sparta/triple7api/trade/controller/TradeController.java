package com.sparta.triple7api.trade.controller;

import com.sparta.triple7api.common.dto.AuthUser;
import com.sparta.triple7api.trade.dto.request.TradeRequestDto;
import com.sparta.triple7api.trade.dto.response.TradeResponseDto;
import com.sparta.triple7api.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cryptos")
public class TradeController {

    private final TradeService tradeService;

    @PostMapping("/{cryptoId}/trades")
    public ResponseEntity<TradeResponseDto> postTrade(@AuthenticationPrincipal AuthUser authUser,
                                                      @PathVariable long cryptoId,
                                                      @RequestBody TradeRequestDto tradeRequestDto){
        return ResponseEntity.ok(tradeService.postTrade(authUser,cryptoId,tradeRequestDto));
    }

    @PostMapping("/")

    @GetMapping("/{cryptoId}/trades")
    public ResponseEntity<List<TradeResponseDto>> getTradeList(@AuthenticationPrincipal AuthUser authUser,
                                                           @PathVariable long cryptoId){
        return ResponseEntity.ok(tradeService.getTradeList(authUser,cryptoId));
    }

    @GetMapping("/trades")
    public ResponseEntity<List<TradeResponseDto>> getAllTradeList(@AuthenticationPrincipal AuthUser authUser){
        return ResponseEntity.ok(tradeService.getAllTradeList(authUser));
    }
}
