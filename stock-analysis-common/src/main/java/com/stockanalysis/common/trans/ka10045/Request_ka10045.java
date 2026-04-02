package com.stockanalysis.common.trans.ka10045;

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
public class Request_ka10045 implements CommBody {
    /** 종목코드 (예: KRX:039490, NXT:039490_NX, SOR:039490_AL) */
    @JsonProperty("stk_cd")
    private String stkCd;

    /** 시작일자 (YYYYMMDD) */
    @JsonProperty("strt_dt")
    private String strtDt;

    /** 종료일자 (YYYYMMDD) */
    @JsonProperty("end_dt")
    private String endDt;

    /** 기관추정단가구분 (1:매수단가, 2:매도단가) */
    @JsonProperty("orgn_prsm_unp_tp")
    private String orgnPrsmUnpTp;

    /** 외인추정단가구분 (1:매수단가, 2:매도단가) */
    @JsonProperty("for_prsm_unp_tp")
    private String forPrsmUnpTp;

}

