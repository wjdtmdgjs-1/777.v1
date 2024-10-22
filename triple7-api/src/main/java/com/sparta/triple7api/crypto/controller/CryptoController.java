package com.sparta.triple7api.crypto.controller;

import com.sparta.triple7api.crypto.dto.CryptoResponse;
import com.sparta.triple7api.crypto.service.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CryptoController {

    private final CryptoService cryptoService;

    @GetMapping("/crypto/{cryptoId}")
    public ResponseEntity<CryptoResponse> getCryptoInfo(@PathVariable Long cryptoId) {
        return ResponseEntity.ok(this.cryptoService.getCryptoInfo(cryptoId));
    }
}
