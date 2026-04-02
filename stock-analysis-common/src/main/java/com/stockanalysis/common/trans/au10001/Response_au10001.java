package com.stockanalysis.common.trans.au10001;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockanalysis.common.CommBody;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Response_au10001 implements CommBody {
    @JsonProperty("expires_dt")
    private String expiresDt;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("token")
    private String token;
}
