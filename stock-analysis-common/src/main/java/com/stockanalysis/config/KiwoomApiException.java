package com.stockanalysis.config;

import lombok.Getter;

@Getter
public class KiwoomApiException extends RuntimeException {
    private final int statusCode;
    private final String responseBody;

    public KiwoomApiException(int statusCode, String responseBody) {
        super("Kiwoom API error. statusCode=" + statusCode);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public KiwoomApiException(String message) {
        super(message);
        this.statusCode = -1;
        this.responseBody = null;
    }

    public KiwoomApiException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = -1;
        this.responseBody = null;
    }

    public KiwoomApiException(int statusCode, String responseBody, String message) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

}
