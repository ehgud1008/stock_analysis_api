package com.stockanalysis.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "kiwoom")
public class KiwoomProperties {
    /**
     * 실전 API Base URL
     */
    private String baseUrl;

    /**
     * 모의 API Base URL
     */
    private String mockBaseUrl;

    /**
     * 모의/실전 전환 플래그
     */
    private boolean useMock;

    /**
     * 네트워크 타임아웃(밀리초)
     */
    private long timeoutMs = 5000;

    /**
     * OAuth2 grant type (기본: client_credentials)
     */
    private String grantType = "client_credentials";

    /**
     * Kiwoom REST appkey
     */
    private String appKey;

    /**
     * Kiwoom REST secretkey
     */
    private String secretKey;

    public String getActiveBaseUrl() {
        return useMock ? mockBaseUrl : baseUrl;
    }
}
