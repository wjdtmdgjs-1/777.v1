package com.sparta.triple7api.user.controller;

import com.sparta.triple7api.common.dto.AuthUser;
import com.sparta.triple7api.user.dto.request.UserChangePasswordRequest;
import com.sparta.triple7api.user.dto.request.UserWithdrawRequest;
import com.sparta.triple7api.user.dto.response.UserResponse;
import com.sparta.triple7api.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @DeleteMapping("/users")
    public void withdrawUser(@AuthenticationPrincipal AuthUser authUser,
                             @Valid @RequestBody UserWithdrawRequest userWithdrawRequest
    ) {
        userService.withdrawUser(authUser, userWithdrawRequest);
    }

    @PatchMapping("/users")
    public void changePassword(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody UserChangePasswordRequest userChangePasswordRequest
    ) {
        userService.changePassword(authUser, userChangePasswordRequest);
    }
}
