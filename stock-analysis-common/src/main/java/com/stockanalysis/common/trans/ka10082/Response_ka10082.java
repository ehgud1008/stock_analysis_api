// ka10082 Response DTO
package com.stockanalysis.common.trans.ka10082;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockanalysis.common.CommBody;
import com.stockanalysis.common.trans.querys.StkDtChartQryItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response_ka10082 implements CommBody {
    @JsonProperty("stk_cd")
    private String stkCd;

    @JsonProperty("stk_min_chart_qry")
    private List<StkDtChartQryItem> stkMinChartQry;

    @JsonProperty("return_code")
    private int returnCode;

    @JsonProperty("return_msg")
    private String returnMsg;
}