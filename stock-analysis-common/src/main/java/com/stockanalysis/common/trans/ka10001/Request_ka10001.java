package com.stockanalysis.common.trans.ka10001;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockanalysis.common.CommBody;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Request_ka10001 implements CommBody {
    @JsonProperty("stk_cd")
    private String stkCd;
}

