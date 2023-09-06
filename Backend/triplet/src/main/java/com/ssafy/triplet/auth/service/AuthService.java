package com.ssafy.triplet.auth.service;

import com.ssafy.triplet.auth.dto.AuthRequestDto;
import com.ssafy.triplet.auth.dto.AuthResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    //1원이체 api
    public Optional<AuthResponseDto> getOneTransferResponse(AuthRequestDto request) {
        return Optional.of(null);
    }
}
