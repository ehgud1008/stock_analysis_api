package com.stockanalysis.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockanalysis.common.CommRequest;
import com.stockanalysis.common.au10001.Request_au10001;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${kiwoom.grant_type}")
    private String grant_type;
    @Value("${kiwoom.app-key}")
    private String app_key;
    @Value("${kiwoom.secret-key}")
    private String secret_key;

    @PostMapping("/auth")
    public String fn_au10001(@RequestBody CommRequest<Request_au10001> request) {
        try {
            // 1. 요청할 API URL
            // String host = "https://mockapi.kiwoom.com"; // 모의투자
            String host = "https://api.kiwoom.com"; // 실전투자
            String endpoint = "/oauth2/token";
            String urlString = host + endpoint;

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 2. Header 데이터 설정
            connection.setRequestMethod("POST"); // 메서드 타입
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8"); // 컨텐츠타입
            connection.setDoOutput(true);

            Request_au10001 req = new Request_au10001();

            req.setGrantType(grant_type);
            req.setAppkey(app_key);
            req.setSecretkey(secret_key);

            String jsonBody = mapper.writeValueAsString(req);

            // 3. JSON 데이터 전송
            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonBody.getBytes(StandardCharsets.UTF_8));
            }

            // 4. 응답 헤더 출력
            System.out.println("Code: "+ connection.getResponseCode());
            System.out.println("Header:");
            String[] headerKeys = {"cont-yn","next-key","api-id"};
            connection.getHeaderFields().forEach((key, value) -> {
                if(Arrays.asList(headerKeys).contains(key)){
                    System.out.println("    " + key + ": " + value.get(0));
                }
            });

            // 5. 응답 본문 출력
            System.out.println("Body:");
            try (Scanner scanner = new Scanner(connection.getInputStream(), "utf-8")) {
                String responseBody = scanner.useDelimiter("\\A").next();
                System.out.println("    " + responseBody);
                return responseBody;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
