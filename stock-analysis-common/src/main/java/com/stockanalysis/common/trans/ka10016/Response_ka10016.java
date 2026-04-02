package com.stockanalysis.common.trans.ka10016;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockanalysis.common.CommBody;
import com.stockanalysis.common.trans.querys.HighLowPricItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
/**
 * 신고저가요청(ka10016) 응답 DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response_ka10016 implements CommBody {

    /** 신고저가 목록 */
    @JsonProperty("ntl_pric")
    private List<HighLowPricItem> ntlPric;

    /** 공통 반환 코드 */
    @JsonProperty("return_code")
    private int returnCode;

    /** 공통 반환 메시지 */
    @JsonProperty("return_msg")
    private String returnMsg;
}