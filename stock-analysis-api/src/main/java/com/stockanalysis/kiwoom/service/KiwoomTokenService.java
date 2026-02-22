package com.stockanalysis.kiwoom.service;

import com.stockanalysis.common.au10001.Request_au10001;
import com.stockanalysis.common.au10001.Response_au10001;
import com.stockanalysis.config.KiwoomApiException;
import com.stockanalysis.config.KiwoomProperties;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Kiwoom OAuth2 client_credentials 토큰 발급 및 캐싱.
 */
@Service
@RequiredArgsConstructor
public class KiwoomTokenService {

    /**
     * Kiwoom REST OAuth2 토큰 발급 경로
     */
    public static final String AUTH_PATH = "/oauth2/token";

    private static final ZoneId KST = ZoneId.of("Asia/Seoul");
    private static final DateTimeFormatter EXPIRES_FMT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final WebClient kiwoomWebClient;
    private final KiwoomProperties properties;

    private final AtomicReference<TokenCache> cache = new AtomicReference<>();

    /**
     * "Bearer <token>" 형태의 authorization 헤더 값을 반환.
     */
    public String getBearerAuthorizationValue() {
        TokenCache current = cache.get();
        if (current != null && current.isValid()) {
            return current.authorizationValue();
        }

        synchronized (this) {
            TokenCache again = cache.get();
            if (again != null && again.isValid()) {
                return again.authorizationValue();
            }
            TokenCache refreshed = fetchNewToken();
            cache.set(refreshed);
            return refreshed.authorizationValue();
        }
    }

    private TokenCache fetchNewToken() {
        if (!StringUtils.hasText(properties.getAppKey()) || !StringUtils.hasText(properties.getSecretKey())) {
            throw new KiwoomApiException(
                    "Kiwoom appKey/secretKey is not configured. Set kiwoom.app-key and kiwoom.secret-key (or env vars)."
            );
        }

        Request_au10001 req = new Request_au10001();
        req.setGrantType(StringUtils.hasText(properties.getGrantType()) ? properties.getGrantType() : "client_credentials");
        req.setAppkey(properties.getAppKey());
        req.setSecretkey(properties.getSecretKey());

        Response_au10001 resp = kiwoomWebClient
                .post()
                .uri(AUTH_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(req)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResp ->
                        clientResp.bodyToMono(String.class)
                                .defaultIfEmpty("")
                                .flatMap(body -> Mono.error(new KiwoomApiException(
                                        clientResp.statusCode().value(),
                                        body,
                                        "Kiwoom token request failed: HTTP " + clientResp.statusCode().value()
                                )))
                )
                .bodyToMono(Response_au10001.class)
                .block(Duration.ofSeconds(10));

        if (resp == null || !StringUtils.hasText(resp.getToken())) {
            throw new KiwoomApiException("Kiwoom token response is empty.");
        }

        Instant expiresAt = parseExpiresAt(resp.getExpiresDt()).minusSeconds(60); // 60초 버퍼

        String tokenType = StringUtils.hasText(resp.getTokenType()) ? resp.getTokenType() : "bearer";
        String tokenTypeNormalized = tokenType.equalsIgnoreCase("bearer") ? "Bearer" : tokenType;

        return new TokenCache(tokenTypeNormalized + " " + resp.getToken(), expiresAt);
    }

    private Instant parseExpiresAt(String expiresDt) {
        // 공식 가이드 응답 예: "20241107083713"
        if (!StringUtils.hasText(expiresDt)) {
            return Instant.now().plus(Duration.ofHours(1));
        }
        try {
            LocalDateTime ldt = LocalDateTime.parse(expiresDt, EXPIRES_FMT);
            return ldt.atZone(KST).toInstant();
        } catch (Exception e) {
            // 포맷이 다를 경우 1시간으로 보수적으로 fallback
            return Instant.now().plus(Duration.ofHours(1));
        }
    }

    private record TokenCache(String authorizationValue, Instant expiresAt) {
        boolean isValid() {
            return Instant.now().isBefore(expiresAt);
        }
    }
}
