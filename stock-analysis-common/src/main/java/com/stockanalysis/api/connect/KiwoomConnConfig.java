package com.stockanalysis.api.connect;

import com.stockanalysis.config.KiwoomProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(KiwoomProperties.class)
public class KiwoomConnConfig {
    @Bean
    public WebClient kiwoomWebClient(KiwoomProperties props) {
        // 사용 이유:
        // - keep-alive, connection pooling, timeout 등 설정이 쉬움
        // - 요청/응답 로깅/에러처리 확장 용이
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofMillis(props.getTimeoutMs()));

        // 큰 JSON(차트 데이터 등) 대비: 버퍼 사이즈 조정(기본 256KB)
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(cfg -> cfg.defaultCodecs().maxInMemorySize(100 * 1024 * 1024))
                .build();

        return WebClient.builder()
                .baseUrl(props.getActiveBaseUrl())
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(strategies)
                .build();
    }
}
