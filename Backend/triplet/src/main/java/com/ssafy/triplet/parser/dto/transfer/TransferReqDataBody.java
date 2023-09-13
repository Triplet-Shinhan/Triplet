package com.ssafy.triplet.parser.dto.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferReqDataBody {

    @JsonProperty("출금계좌번호")
    private String withdrawalAccountNum;

    @JsonProperty("입금은행코드")
    private String depositBankCode;

    @JsonProperty("입금계좌번호")
    private String depositAccountNum;

    @JsonProperty("이체금액")
    private String transferAmount;

    @JsonProperty("입금계좌통장메모")
    private String depositAccountMemo;

    @JsonProperty("출금계좌통장메모")
    private String withdrawalAccountMemo;
}
