package com.stockanalysis.api.route;

import com.stockanalysis.common.CommBody;

/**
 * api-id 별 요청/응답 전문 스펙.
 */
public record KiwoomTrSpec(
        Class<? extends CommBody> requestClass,
        Class<? extends CommBody> responseClass
) {}
