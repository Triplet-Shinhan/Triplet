package com.ssafy.triplet.exchange.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearBranchResponseDto {

    private int resultCode;
    private int listNum;
    private List<BranchInfo> dataList;

}
