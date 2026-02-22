package com.stockanalysis.kiwoom.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.stockanalysis.api.constants.ApiConstants;
import com.stockanalysis.api.route.KiwoomTrMeta;
import com.stockanalysis.api.route.KiwoomTrRegistry;
import com.stockanalysis.api.route.KiwoomTrSpec;
import com.stockanalysis.common.CommHeaders;
import com.stockanalysis.common.CommRequest;
import com.stockanalysis.config.KiwoomApiException;
import com.stockanalysis.kiwoom.dto.KiwoomProxyRequest;
import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Kiwoom REST API 프록시 게이트웨이.
 *
 * - api-id, cont-yn, next-key, authorization 등을 받아 키움 REST로 전달
 * - authorization이 없으면 서버가 /oauth2/token으로 발급/캐시 후 자동 주입
 */
@Service
@RequiredArgsConstructor
public class KiwoomApiGateway {

    private final WebClient kiwoomWebClient;
    private final KiwoomTokenService tokenService;
    private final KiwoomTrCodec trCodec;

    public ResponseEntity<String> proxy(CommRequest<?> req) {
        if (req == null) {
            throw new KiwoomApiException("request is required.");
        }

        CommHeaders ch = req.getHeader();
        if (ch == null || !StringUtils.hasText(ch.getApiId())) {
            throw new KiwoomApiException("전문코드를 확인해주세요.");
        }

//        String endpoint = resolveEndpoint(req);
//        boolean isAuthEndpoint = isAuthEndpoint(endpoint, ch.getApiId());

        HttpHeaders outbound = new HttpHeaders();
        outbound.setContentType(MediaType.APPLICATION_JSON);
        outbound.setAccept(List.of(MediaType.APPLICATION_JSON));

        String auth = normalizeAuthorization(ch.getAuthorization());
        if (!StringUtils.hasText(auth)) {
//            auth = tokenService.getBearerAuthorizationValue();
            throw new KiwoomApiException("토큰값 발급 필요.");
        }

        outbound.set("authorization", auth);
        outbound.set("cont-yn", StringUtils.hasText(ch.getContYn()) ? ch.getContYn() : "N");
        outbound.set("next-key", StringUtils.hasText(ch.getNextKey()) ? ch.getNextKey() : "");
        outbound.set("api-id", ch.getApiId());

        JsonNode bodyNode = req.getBody();

        if (bodyNode == null || bodyNode.isNull()) {
            throw new KiwoomApiException("Body 값 확인.");
        }

        // api-id에 맞는 전문 모델로 파싱(등록된 경우에만). 이후 다시 JSON으로 직렬화되어 전송됨.
        Object outboundBody = trCodec.tryParseRequestBody(ch.getApiId(), bodyNode);
        if (outboundBody == null) {
//            outboundBody = bodyNode;
            throw new KiwoomApiException("등록된 전문이 없습니다.");
        }

        String endpoint = KiwoomTrRegistry.endpointOf(ch.getApiId())
                .orElseThrow(() -> new KiwoomApiException("미지원 전문코드(api-id): " + ch.getApiId()));

        ResponseEntity<String> response = kiwoomWebClient
                .post()
                .uri(ApiConstants.API_URL + endpoint)
                .headers(h -> h.addAll(outbound))
                .bodyValue(outboundBody)
                .exchangeToMono(clientResp -> toResponseEntity(clientResp, ch.getApiId()))
                .block(Duration.ofSeconds(15));
        return response;
    }

    private String normalizeAuthorization(String authorization) {
        if (!StringUtils.hasText(authorization)) {
            return null;
        }
        String trimmed = authorization.trim();
        if (trimmed.regionMatches(true, 0, "Bearer ", 0, "Bearer ".length())) {
            return trimmed;
        }
        // 사용자가 token 값만 넘긴 경우도 처리
        return "Bearer " + trimmed;
    }

    private Mono<ResponseEntity<String>> toResponseEntity(ClientResponse clientResp, String apiId) {
        HttpHeaders h = new HttpHeaders();
        copyHeaderIfPresent(clientResp, h, "cont-yn");
        copyHeaderIfPresent(clientResp, h, "next-key");

        // api-id는 응답에 없을 수 있으니 요청 apiId로 보정해 넣는게 안전
        h.set("api-id", apiId);

        return clientResp.bodyToMono(String.class)
                .defaultIfEmpty("")
                .map(body -> ResponseEntity
                        .status(clientResp.statusCode())
                        .headers(h)
                        .body(body));
    }

    private void copyHeaderIfPresent(ClientResponse resp, HttpHeaders target, String name) {
        String v = resp.headers().asHttpHeaders().getFirst(name);
        if (v != null) target.set(name, v);
    }
}
