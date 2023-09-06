package com.ssafy.triplet.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    private String bankCode;
    private String receiptsAccount;
}
