package com.stockanalysis.common.trans.ka10032;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockanalysis.common.CommBody;
import com.stockanalysis.common.trans.querys.TrdePricaUpperItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 거래대금상위요청
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Response_ka10032 implements CommBody {
    @JsonProperty("trde_prica_upper")
    private List<TrdePricaUpperItem> trdePricaUpper;

    @JsonProperty("return_code")
    private int returnCode;

    @JsonProperty("return_msg")
    private String returnMsg;

}
