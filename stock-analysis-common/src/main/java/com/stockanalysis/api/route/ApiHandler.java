package com.stockanalysis.api.route;

import com.stockanalysis.common.CommBody;
import com.stockanalysis.common.CommHeaders;

public interface ApiHandler<REQ extends CommBody, RES extends CommBody> {
    String apiId();

    Class<REQ> requestType();
    Class<RES> responseType();

    // 외부 호출 정보
    String url();
    default String method() { return "POST"; }         // 필요시 GET 등으로 override
    default boolean sendBodyOnly() { return true; }    // 외부에 body만 보낼지 여부

    // 내부 header -> 외부 header 매핑 규칙이 API마다 다르면 override
    default void applyOutboundHeaders(CommHeaders in, java.net.HttpURLConnection conn) {
        if (in != null && in.getAuthorization() != null && !in.getAuthorization().isBlank()) {
            conn.setRequestProperty("Authorization", in.getAuthorization().trim());
        }
    }

    // 응답 후처리가 필요하면 override
    default RES postProcess(RES res) { return res; }
}