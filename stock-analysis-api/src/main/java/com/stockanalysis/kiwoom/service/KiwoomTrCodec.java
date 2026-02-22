package com.stockanalysis.kiwoom.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockanalysis.common.CommBody;
import java.io.IOException;

import com.stockanalysis.config.KiwoomApiException;
import com.stockanalysis.api.route.KiwoomTrRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * api-id(TR명)에 맞춰 요청/응답 바디를 stock-analysis-common 모델로 파싱하는 유틸.
 */
@Component
@RequiredArgsConstructor
public class KiwoomTrCodec {

    private final ObjectMapper objectMapper;

    /**
     * api-id가 레지스트리에 등록된 경우에만 요청 바디를 DTO로 파싱한다.
     * - 미등록 api-id는 null 반환(프록시는 그대로 진행 가능)
     * - 등록 api-id인데 파싱 실패면 예외(요청 스펙 불일치)
     */
    public CommBody tryParseRequestBody(String apiId, JsonNode body) {
        if (!StringUtils.hasText(apiId) || body == null || body.isNull()) {
            return null;
        }

        Class<? extends CommBody> clazz = KiwoomTrRegistry.requestClassOf(apiId).orElse(null);
        if (clazz == null) {
            return null;
        }

        try {
            return objectMapper.treeToValue(body, clazz);
        } catch (Exception e) {
            throw new KiwoomApiException("요청 전문 파싱 실패(api-id=" + apiId + "): " + e.getMessage());
        }
    }

//    /**
//     * 응답 JSON을 전문 모델로 파싱한다. (등록된 responseClass가 없으면 null 반환)
//     */
//    public CommBody tryParseResponseBody(String apiId, String responseJson) {
//        if (!StringUtils.hasText(apiId) || !StringUtils.hasText(responseJson)) {
//            return null;
//        }
//        Class<? extends CommBody> clazz = KiwoomTrRegistry.responseClassOf(apiId).orElse(null);
//        if (clazz == null) {
//            return null;
//        }
//        try {
//            return objectMapper.readValue(responseJson, clazz);
//        } catch (IOException e) {
//            return null;
//        }
//    }
}
