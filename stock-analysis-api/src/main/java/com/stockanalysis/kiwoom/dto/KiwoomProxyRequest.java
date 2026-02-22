package com.stockanalysis.kiwoom.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.stockanalysis.common.CommHeaders;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * /api/json 요청 바디
 *
 * 예)
 * {
 *   "commHeaders": {
 *     "api-id": "ka10081",
 *     "authorization": "Bearer <accessToken>",   // 없으면 서버가 토큰 발급(캐시) 후 자동 주입
 *     "cont-yn": "N",
 *     "next-key": ""
 *   },
 *   "endpoint": "/api/dostk/chart",              // 생략 가능 (기본: api-id가 au10001이면 /oauth2/token, 그 외는 /api/dostk/chart)
 *   "body": {
 *     "stk_cd": "005930",
 *     "base_dt": "20250908",
 *     "upd_stkpc_tp": "1"
 *   }
 * }
 */
@Data
public class KiwoomProxyRequest {

    @Valid
    @NotNull
    private CommHeaders commHeaders;

    /**
     * Kiwoom REST endpoint path (예: /api/dostk/chart, /oauth2/token).
     * null/blank 이면 api-id 기반으로 기본값을 선택한다.
     */
    private String endpoint;

    /**
     * Kiwoom REST 요청 Body (임의 JSON).
     */
    @NotNull
    private JsonNode body;
}
