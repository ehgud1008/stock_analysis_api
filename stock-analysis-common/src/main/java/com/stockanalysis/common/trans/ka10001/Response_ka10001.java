package com.stockanalysis.common.trans.ka10001;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockanalysis.common.CommBody;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Response_ka10001 implements CommBody {

    @JsonProperty("stk_cd")
    private String stkCd;                 // 종목코드

    @JsonProperty("stk_nm")
    private String stkNm;                 // 종목명

    @JsonProperty("setl_mm")
    private String setlMm;                // 결산월

    @JsonProperty("fav")
    private String fav;                   // 액면가

    @JsonProperty("cap")
    private String cap;                   // 자본금

    @JsonProperty("flo_stk")
    private String floStk;                // 상장주식

    @JsonProperty("crd_rt")
    private String crdRt;                 // 신용비율

    @JsonProperty("oyr_hgst")
    private String oyrHgst;               // 연중최고

    @JsonProperty("oyr_lwst")
    private String oyrLwst;               // 연중최저

    @JsonProperty("mac")
    private String mac;                   // 시가총액

    @JsonProperty("mac_wght")
    private String macWght;               // 시가총액비중

    @JsonProperty("for_exh_rt")
    private String forExhRt;              // 외인소진률

    @JsonProperty("repl_pric")
    private String replPric;              // 대용가

    @JsonProperty("per")
    private String per;                   // PER

    @JsonProperty("eps")
    private String eps;                   // EPS

    @JsonProperty("roe")
    private String roe;                   // ROE

    @JsonProperty("pbr")
    private String pbr;                   // PBR

    @JsonProperty("ev")
    private String ev;                    // EV

    @JsonProperty("bps")
    private String bps;                   // BPS

    @JsonProperty("sale_amt")
    private String saleAmt;               // 매출액

    @JsonProperty("bus_pro")
    private String busPro;                // 영업이익

    @JsonProperty("cup_nga")
    private String cupNga;                // 당기순이익

    @JsonProperty("250hgst")
    private String twoHundredFiftyHgst;   // 250최고

    @JsonProperty("250lwst")
    private String twoHundredFiftyLwst;   // 250최저

    @JsonProperty("open_pric")
    private String openPric;              // 시가

    @JsonProperty("high_pric")
    private String highPric;              // 고가

    @JsonProperty("low_pric")
    private String lowPric;               // 저가

    @JsonProperty("upl_pric")
    private String uplPric;               // 상한가

    @JsonProperty("lst_pric")
    private String lstPric;               // 하한가

    @JsonProperty("base_pric")
    private String basePric;              // 기준가

    @JsonProperty("exp_cntr_pric")
    private String expCntrPric;           // 예상체결가

    @JsonProperty("exp_cntr_qty")
    private String expCntrQty;            // 예상체결수량

    @JsonProperty("250hgst_pric_dt")
    private String twoHundredFiftyHgstPricDt;     // 250최고가일

    @JsonProperty("250hgst_pric_pre_rt")
    private String twoHundredFiftyHgstPricPreRt;  // 250최고가대비율

    @JsonProperty("250lwst_pric_dt")
    private String twoHundredFiftyLwstPricDt;     // 250최저가일

    @JsonProperty("250lwst_pric_pre_rt")
    private String twoHundredFiftyLwstPricPreRt;  // 250최저가대비율

    @JsonProperty("cur_prc")
    private String curPrc;                // 현재가

    @JsonProperty("pre_sig")
    private String preSig;                // 대비기호

    @JsonProperty("pred_pre")
    private String predPre;               // 전일대비

    @JsonProperty("flu_rt")
    private String fluRt;                 // 등락율

    @JsonProperty("trde_qty")
    private String trdeQty;               // 거래량

    @JsonProperty("trde_pre")
    private String trdePre;               // 거래대비

    @JsonProperty("fav_unit")
    private String favUnit;               // 액면가단위

    @JsonProperty("dstr_stk")
    private String dstrStk;               // 유통주식

    @JsonProperty("dstr_rt")
    private String dstrRt;                // 유통비율
}
