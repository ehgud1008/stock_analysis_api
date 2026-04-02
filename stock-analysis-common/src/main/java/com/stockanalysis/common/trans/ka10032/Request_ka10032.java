package com.stockanalysis.common.trans.ka10032;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockanalysis.common.CommBody;
import lombok.Getter;
import lombok.Setter;

/**
 *
 거래대금상위요청
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Request_ka10032 implements CommBody {
    @JsonProperty("mrkt_tp")
    private String mrktTp;

    @JsonProperty("mang_stk_incls")
    private String mangStkIncl;

    @JsonProperty("stex_tp")
    private String stexTp;

}

