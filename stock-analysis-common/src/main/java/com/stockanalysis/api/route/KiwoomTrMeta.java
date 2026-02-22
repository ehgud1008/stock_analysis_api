package com.stockanalysis.api.route;

import com.stockanalysis.common.CommBody;

public class KiwoomTrMeta {
    private final String endpoint;
    private final Class<? extends CommBody> requestClass;
    private final Class<?> responseClass;

    public KiwoomTrMeta(
            String endpoint,
            Class<? extends CommBody> requestClass,
            Class<?> responseClass
    ) {
        this.endpoint = endpoint;
        this.requestClass = requestClass;
        this.responseClass = responseClass;
    }

    public String endpoint() {
        return endpoint;
    }

    public Class<? extends CommBody> requestClass() {
        return requestClass;
    }

    public Class<?> responseClass() {
        return responseClass;
    }
}
