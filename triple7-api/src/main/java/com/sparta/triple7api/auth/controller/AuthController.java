package com.sparta.triple7api.auth.controller;

import com.sparta.triple7api.auth.dto.request.FindPasswordRequest;
import com.sparta.triple7api.auth.dto.request.ResetPasswordRequest;
import com.sparta.triple7api.auth.dto.request.SigninRequest;
import com.sparta.triple7api.auth.dto.request.SignupRequest;
import com.sparta.triple7api.auth.dto.response.SigninResponse;
import com.sparta.triple7api.auth.dto.response.SignupResponse;
import com.sparta.triple7api.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(authService.signup(signupRequest));
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<SigninResponse> signin(@Valid @RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok(authService.signin(signinRequest));
    }

    @PatchMapping("/auth/reset-password")
    public void resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        authService.resetPassword(resetPasswordRequest);
    }
}
