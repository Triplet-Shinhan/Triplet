package com.ssafy.triplet.auth.controller;

import com.ssafy.triplet.auth.dto.AuthResponseDto;
import com.ssafy.triplet.auth.dto.OneTransferRequestDto;
import com.ssafy.triplet.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("1transfer")
    public String transferOne(@Validated @ModelAttribute OneTransferRequestDto oneTransferRequestDto) {
        authService.requestOneTransfer(oneTransferRequestDto);
        AuthResponseDto response = authService.getOneTransferResponse(oneTransferRequestDto).orElse(null);
        return "";
    }
}