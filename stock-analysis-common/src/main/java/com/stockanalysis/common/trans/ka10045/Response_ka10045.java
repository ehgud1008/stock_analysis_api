package com.stockanalysis.common.trans.ka10045;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockanalysis.common.CommBody;
import com.stockanalysis.common.trans.querys.StkOrgnTrdeTrnsnItem;
import com.stockanalysis.common.trans.querys.TrdePricaUpperItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 종목별기관매매추이요청(ka10045) 응답 DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response_ka10045 implements CommBody {

    /** 기관추정평균가 */
    @JsonProperty("orgn_prsm_avg_pric")
    private String orgnPrsmAvgPric;

    /** 외인추정평균가 */
    @JsonProperty("for_prsm_avg_pric")
    private String forPrsmAvgPric;

    /** 종목별기관매매추이 목록 */
    @JsonProperty("stk_orgn_trde_trnsn")
    private List<StkOrgnTrdeTrnsnItem> stkOrgnTrdeTrnsn;

    /** 공통 반환 코드 */
    @JsonProperty("return_code")
    private int returnCode;

    /** 공통 반환 메시지 */
    @JsonProperty("return_msg")
    private String returnMsg;

}