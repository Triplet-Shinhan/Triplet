package com.ssafy.triplet.exchange.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExchangeResultsResponseDto {
    private Integer resultCode;
    private Integer listNum;
    private List<DataBody> list;

    @Data
    private class DataBody {
        private String state;
        private String name;
        private String telNumber;
        private String birthDate;
        private String applicationDate;
        private String receiptDate;
        private String receiptLocation;
        private String virtualAccountDepositDate;
        private String virtualDepositAccount;
        private String virtualAccountDepositAmount;
        private String deadlineDate;
        private String deadlineTime;
        private String transactionClass;
        private String currency;
        private String exchangeAmount;
        private String preferentialRate;
        private String KRWAmount;
    }
}
