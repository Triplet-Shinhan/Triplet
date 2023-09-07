package com.ssafy.triplet.auth.controller;

import com.ssafy.triplet.auth.dto.OneTransferRequestDto;
import com.ssafy.triplet.auth.service.AuthService;
import com.ssafy.triplet.auth.util.AuthUtility;

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
    private final AuthUtility authUtility;
    @PostMapping("1transfer")
    public String transferOne(@Validated @ModelAttribute OneTransferRequestDto oneTransferRequestDto) {
        String memo = authUtility.getRandomWord();
        authService.sendOne(oneTransferRequestDto, memo);
        return memo;
    }
}
