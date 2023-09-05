package com.ssafy.triplet.parser.dto.rateParser;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataBodyRequest;
import com.ssafy.triplet.parser.dto.DataHeaderRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateReqDto {


        @JsonProperty("dataHeader")
        private DataHeaderRequest dataHeader;

        @JsonProperty("dataBody")
        private DataBodyRequest dataBody;

    }

