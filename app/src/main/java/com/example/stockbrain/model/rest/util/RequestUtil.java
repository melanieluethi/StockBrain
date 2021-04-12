package com.example.stockbrain.model.rest.util;

public class RequestUtil {
    public static String getUrl(String ticker) {
        return RestConstants.BASE_URL + RestConstants.API_KEY_URL + RestConstants.API_KEY + RestConstants.TICKER_URL + ticker + "/";
    }
}