package com.stockanalysis.common.trans.ka10081;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockanalysis.common.CommBody;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 주식일봉차트조회요청 (ka10081)
 * 요청 바디 예:
 * {
 *   "stk_cd": "005930",
 *   "base_dt": "20250908",
 *   "upd_stkpc_tp": "1"
 * }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request_ka10081 implements CommBody {
    @JsonProperty("stk_cd")
    private String stkCd;

    @JsonProperty("base_dt")
    private String baseDt;

    @JsonProperty("upd_stkpc_tp")
    private String updStkpcTp;

}

