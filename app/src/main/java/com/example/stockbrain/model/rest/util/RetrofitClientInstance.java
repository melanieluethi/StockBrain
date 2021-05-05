package com.example.stockbrain.model.rest.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit stockRetrofit;
    private static Retrofit logoRetrofit;

    public static Retrofit getStockRetrofitInstance() {
        if (stockRetrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            stockRetrofit = new retrofit2.Retrofit.Builder()
                    .client(getHttpClient())
                    .baseUrl(RestConstants.BASE_URL_STOCK)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return stockRetrofit;
    }

    private static OkHttpClient getHttpClient(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalUrl = original.url();
                HttpUrl url = originalUrl.newBuilder()
                        .addQueryParameter("api-key", RestConstants.API_KEY_STOCK)
                        .build();
                Request.Builder requestBuilder = original.newBuilder().url(url);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return httpClient.build();
    }

    public static Retrofit getLogoRetrofitInstance() {
        if (logoRetrofit == null) {
            logoRetrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(RestConstants.BASE_URL_LOGO)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return logoRetrofit;
    }
}