package com.example.stockbrain.model.rest.service;

import com.example.stockbrain.model.businessobject.Security;

import retrofit2.Call;

import retrofit2.http.GET;

public interface StockBrainService {
    // TODO: LUM - Change security to company details
    @GET("/security")
    Call<Security> getCompanyDetail();
}