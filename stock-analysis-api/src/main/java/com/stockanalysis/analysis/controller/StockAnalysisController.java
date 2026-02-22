package com.stockanalysis.analysis.controller;

import com.stockanalysis.common.CommRequest;
import com.stockanalysis.kiwoom.dto.KiwoomProxyRequest;
import com.stockanalysis.kiwoom.service.KiwoomApiGateway;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class StockAnalysisController {

    private final KiwoomApiGateway kiwoomApiGateway;

    /**
     * 전문코드(api-id), 공통헤더(cont-yn/next-key/authorization), 요청 파라미터(body)를 받아
     * Kiwoom REST API 로 그대로 전달한 뒤 응답을 반환한다.
     */
    @PostMapping("/json")
    public ResponseEntity<String> proxy(@RequestBody CommRequest<?> req) {
        return kiwoomApiGateway.proxy(req);
    }
}
