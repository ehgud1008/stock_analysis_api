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
public class StkDtChartQryItem {
    @JsonProperty("cur_prc")
    private String curPrc;          //현재가

    @JsonProperty("trde_qty")
    private String trdeQty;         //거래량

    @JsonProperty("trde_prica")
    private String trdePrica;       //거래대금

    @JsonProperty("dt")
    private String dt;              //일자

    @JsonProperty("open_pric")
    private String openPric;        //시가

    @JsonProperty("high_pric")
    private String highPric;        //고가

    @JsonProperty("low_pric")
    private String lowPric;         //저가

    @JsonProperty("pred_pre")
    private String predPre;         //전일대비

    @JsonProperty("pred_pre_sig")
    private String predPreSig;      //전일대비기호

    @JsonProperty("trde_tern_rt")
    private String trdeTernRt;      //거래회전율

}
