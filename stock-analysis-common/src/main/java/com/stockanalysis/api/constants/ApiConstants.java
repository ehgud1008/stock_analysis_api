package com.stockanalysis.api.constants;

import org.springframework.beans.factory.annotation.Value;

public class ApiConstants {

    public final static String API_URL = "https://api.kiwoom.com";

    public final static String API_URL_AUTH = "/oauth2/token";
    public final static String API_URL_CHART = "/api/dostk/chart";
    public final static String API_URL_STKINFO = "/api/dostk/stkinfo";
    public final static String API_URL_RKINFO = "/api/dostk/rkinfo";
}
