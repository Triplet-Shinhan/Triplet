package com.ssafy.triplet.auth.controller;

import com.ssafy.triplet.auth.dto.AuthRequestDto;
import com.ssafy.triplet.auth.dto.AuthResponseDto;
import com.ssafy.triplet.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/v1/auth/1transfer")
    public String transferOne(@Validated @ModelAttribute AuthRequestDto request) {
        AuthResponseDto response = authService.getOneTransferResponse(request).orElse(null);

        return "";
    }
}