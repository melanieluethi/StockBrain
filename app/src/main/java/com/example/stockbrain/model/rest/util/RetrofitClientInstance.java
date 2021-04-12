package com.example.stockbrain.model.rest.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(String ticker) {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                  //  .baseUrl(RequestUtil.getUrl(ticker))
                    .baseUrl(RestConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}