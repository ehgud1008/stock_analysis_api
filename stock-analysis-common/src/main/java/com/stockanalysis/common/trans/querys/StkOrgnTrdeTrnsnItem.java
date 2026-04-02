package com.stockanalysis.common.trans.querys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 종목별기관매매추이 아이템
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StkOrgnTrdeTrnsnItem {
    /** 일자 */
    @JsonProperty("dt")
    private String dt;

    /** 종가 */
    @JsonProperty("close_pric")
    private String closePric;

    /** 대비기호 */
    @JsonProperty("pre_sig")
    private String preSig;

    /** 전일대비 */
    @JsonProperty("pred_pre")
    private String predPre;

    /** 등락율 */
    @JsonProperty("flu_rt")
    private String fluRt;

    /** 거래량 */
    @JsonProperty("trde_qty")
    private String trdeQty;

    /** 기관기간누적 */
    @JsonProperty("orgn_dt_acc")
    private String orgnDtAcc;

    /** 기관일별순매매수량 */
    @JsonProperty("orgn_daly_nettrde_qty")
    private String orgnDalyNettrdeQty;

    /** 외인기간누적 */
    @JsonProperty("for_dt_acc")
    private String forDtAcc;

    /** 외인일별순매매수량 */
    @JsonProperty("for_daly_nettrde_qty")
    private String forDalyNettrdeQty;

    /** 한도소진율 */
    @JsonProperty("limit_exh_rt")
    private String limitExhRt;
}
