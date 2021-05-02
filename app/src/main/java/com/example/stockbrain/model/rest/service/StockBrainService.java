package com.example.stockbrain.model.rest.service;

import com.example.stockbrain.model.rest.pojo.CompanyPojo;
import com.example.stockbrain.model.rest.pojo.CompanyStatementsPojo;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StockBrainService {
    @GET("/api/v2/companies/general")
    Call<List<CompanyPojo>> getCompany(@Query("ticker") String ticker);

    @GET("/api/v2/companies/prices")
    Call<List<CompanyPojo>> getCompanyPrices(@Query("ticker") String ticker);

    @GET("/api/v2/companies/statements")
    Call<List<CompanyStatementsPojo>> getCompanyStatements(@Query("ticker") String ticker,
                                                          @Query("statement") String statement,
                                                          @Query("period") String period,
                                                          @Query("fyear") Integer fYear);
}