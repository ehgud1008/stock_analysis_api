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
public class TrdePricaUpperItem {
    @JsonProperty("stk_cd")
    private String stkCd;

    @JsonProperty("now_rank")
    private String nowRank;

    @JsonProperty("pred_rank")
    private String predRank;

    @JsonProperty("stk_nm")
    private String stkNm;

    @JsonProperty("cur_prc")
    private String curPrc;

    @JsonProperty("pred_pre_sig")
    private String predPreSig;

    @JsonProperty("pred_pre")
    private String predPre;

    @JsonProperty("flu_rt")
    private String fluRt;

    @JsonProperty("sel_bid")
    private String selBid;

    @JsonProperty("buy_bid")
    private String buyBid;

    @JsonProperty("now_trde_qty")
    private String nowTrdeQty;

    @JsonProperty("pred_trde_qty")
    private String predTrdeQty;

    @JsonProperty("trde_prica")
    private String trdePrica;

}
