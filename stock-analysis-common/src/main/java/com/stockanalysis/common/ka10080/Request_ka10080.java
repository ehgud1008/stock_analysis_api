// ka10080 Request DTO
package com.stockanalysis.common.ka10080;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockanalysis.common.CommBody;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request_ka10080 implements CommBody {
    @JsonProperty("stk_cd")
    private String stkCd;

    @JsonProperty("base_dt")
    private String baseDt;

    @JsonProperty("upd_stkpc_tp")
    private String updStkpcTp;
}