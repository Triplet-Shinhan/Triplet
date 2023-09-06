package com.ssafy.triplet.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AuthResponseDto {
    private Long successCode;
    private String resultCode;
    private String resultMessage;
}
