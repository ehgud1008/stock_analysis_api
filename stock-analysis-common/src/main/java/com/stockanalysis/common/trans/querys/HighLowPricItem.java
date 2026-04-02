package com.stockanalysis.common.trans.querys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HighLowPricItem {
    /** 종목코드 */
    @JsonProperty("stk_cd")
    private String stkCd;

    /** 종목명 */
    @JsonProperty("stk_nm")
    private String stkNm;

    /** 현재가 */
    @JsonProperty("cur_prc")
    private String curPrc;

    /** 전일대비기호 */
    @JsonProperty("pred_pre_sig")
    private String predPreSig;

    /** 전일대비 */
    @JsonProperty("pred_pre")
    private String predPre;

    /** 등락률 */
    @JsonProperty("flu_rt")
    private String fluRt;

    /** 거래량 */
    @JsonProperty("trde_qty")
    private String trdeQty;

    /** 전일거래량대비율 */
    @JsonProperty("pred_trde_qty_pre_rt")
    private String predTrdeQtyPreRt;

    /** 매도호가 */
    @JsonProperty("sel_bid")
    private String selBid;

    /** 매수호가 */
    @JsonProperty("buy_bid")
    private String buyBid;

    /** 고가 */
    @JsonProperty("high_pric")
    private String highPric;

    /** 저가 */
    @JsonProperty("low_pric")
    private String lowPric;

}
