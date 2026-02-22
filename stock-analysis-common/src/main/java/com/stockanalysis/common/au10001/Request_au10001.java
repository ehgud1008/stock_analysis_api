package com.stockanalysis.common.au10001;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockanalysis.common.CommBody;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Request_au10001 implements CommBody {
    @JsonProperty("grant_type")
    private String grantType;

    @JsonProperty("appkey")
    private String appkey;

    @JsonProperty("secretkey")
    private String secretkey;

}

