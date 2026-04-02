package com.stockanalysis.common.trans.ka10016;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockanalysis.common.CommBody;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 신고저가요청(ka10016) 요청 DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request_ka10016 implements CommBody {

    /** 시장구분 (000:전체, 001:코스피, 101:코스닥) */
    @JsonProperty("mrkt_tp")
    private String mrktTp;

    /** 신고저구분 (1:신고가, 2:신저가) */
    @JsonProperty("ntl_tp")
    private String ntlTp;

    /** 고저종구분 (1:고저기준, 2:종가기준) */
    @JsonProperty("high_low_close_tp")
    private String highLowCloseTp;

    /** 종목조건 (0:전체조회, 1:관리종목제외, 3:우선주제외, 5:증100제외, 6:증100만보기, 7:증40만보기, 8:증30만보기) */
    @JsonProperty("stk_cnd")
    private String stkCnd;

    /** 거래량구분 (00000:전체조회, 00010:만주이상, 00050:5만주이상, 00100:10만주이상, 00150:15만주이상, 00200:20만주이상, 00300:30만주이상, 00500:50만주이상, 01000:백만주이상) */
    @JsonProperty("trde_qty_tp")
    private String trdeQtyTp;

    /** 신용조건 (0:전체조회, 1:A군, 2:B군, 3:C군, 4:D군, 7:E군, 9:전체) */
    @JsonProperty("crd_cnd")
    private String crdCnd;

    /** 상하한포함 (0:미포함, 1:포함) */
    @JsonProperty("updown_incls")
    private String updownIncls;

    /** 기간 (5, 10, 20, 60, 250) */
    @JsonProperty("dt")
    private String dt;

    /** 거래소구분 (1:KRX, 2:NXT, 3:통합) */
    @JsonProperty("stex_tp")
    private String stexTp;
}

