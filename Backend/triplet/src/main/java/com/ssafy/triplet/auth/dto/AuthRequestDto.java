package com.ssafy.triplet.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {
    private String bankCode;
    private String accountNum;
    private String memo;
}
