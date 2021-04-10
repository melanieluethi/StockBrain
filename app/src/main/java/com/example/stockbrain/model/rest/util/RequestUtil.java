package com.example.stockbrain.model.rest.util;

public class RequestUtil {
    public static String getUrl(String symbol) {
        return RestConstants.BASE_URL + RestConstants.ACCCESS_URL + RestConstants.ACCESS_KEY + RestConstants.SYMBOLS_URL + symbol + "/";
    }
}