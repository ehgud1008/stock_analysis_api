package com.stockanalysis.api.route;

import com.stockanalysis.api.constants.ApiConstants;
import com.stockanalysis.common.CommBody;
import com.stockanalysis.common.au10001.Request_au10001;
import com.stockanalysis.common.au10001.Response_au10001;
import com.stockanalysis.common.ka10081.Request_ka10081;
import com.stockanalysis.common.ka10081.Response_ka10081;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Kiwoom REST api-id(TR명) -> 전문 모델 매핑 레지스트리.
 *
 * 필요 시 여기에 신규 TR을 추가한다.
 */
public final class KiwoomTrRegistry {

    private static final Map<String, KiwoomTrMeta> SPECS;

    static {
        Map<String, KiwoomTrMeta> m = new HashMap<>();

        // OAuth2 token
        m.put("au10001", new KiwoomTrMeta(
                            ApiConstants.API_URL_AUTH,
                            Request_au10001.class,
                            Response_au10001.class));

        // Charts
//        m.put("ka10079", new KiwoomTrSpec(Request_ka10079.class, Response_ka10079.class));
        m.put("ka10081", new KiwoomTrMeta(
                            ApiConstants.API_URL_CHART,
                            Request_ka10081.class,
                            Response_ka10081.class));

        SPECS = Collections.unmodifiableMap(m);
    }

    private KiwoomTrRegistry() {}

    public static Optional<KiwoomTrMeta> find(String apiId) {
        if (apiId == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(SPECS.get(apiId.toLowerCase()));
    }

    public static Map<String, KiwoomTrMeta> all() {
        return SPECS;
    }

    public static Optional<Class<? extends CommBody>> requestClassOf(String apiId) {
        return find(apiId).map(KiwoomTrMeta::requestClass);
    }

    public static Optional<Class<?>> responseClassOf(String apiId) {
        return find(apiId).map(KiwoomTrMeta::responseClass);
    }

    public static Optional<String> endpointOf(String apiId) {
        return find(apiId).map(KiwoomTrMeta::endpoint);
    }
}
