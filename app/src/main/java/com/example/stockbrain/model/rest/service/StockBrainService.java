package com.example.stockbrain.model.rest.service;

import com.example.stockbrain.model.rest.pojo.CompanyPojo;
import com.example.stockbrain.model.rest.pojo.CompanyPricesPojo;
import com.example.stockbrain.model.rest.pojo.CompanyStatementsPojo;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StockBrainService {
    @GET("/general?")
    Call<CompanyPojo> getCompany(@Query("api-key") String apiKey, @Query("&ticker") String ticker);

    @GET("/prices?")
    Call<List<CompanyPricesPojo>> getCompanyPrices(@Query("api-key") String apiKey, @Query("&ticker") String ticker);

    @GET("/statements?")
    Call<List<CompanyStatementsPojo>> getCompanyStatements(@Query("api-key") String apiKey,
                                                          @Query("&ticker") String ticker,
                                                          @Query("&statement") String statement,
                                                          @Query("&period") String period,
                                                          @Query("&fyear") Integer fYear);
}