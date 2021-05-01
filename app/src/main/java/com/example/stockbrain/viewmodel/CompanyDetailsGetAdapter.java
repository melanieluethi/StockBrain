package com.example.stockbrain.viewmodel;

import android.util.Log;

import com.example.stockbrain.model.rest.pojo.CompanyPojo;
import com.example.stockbrain.model.rest.pojo.CompanyStatementsPojo;
import com.example.stockbrain.model.rest.service.StockBrainService;
import com.example.stockbrain.model.rest.util.RestConstants;
import com.example.stockbrain.model.rest.util.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyDetailsGetAdapter {
    // TODO LUM: handling what happens if successful or failed
    public static void getCompanyGeneral(String ticker) {
        StockBrainService service = RetrofitClientInstance.getRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyPojo>> call = service.getCompany(ticker);
        call.enqueue(new Callback<List<CompanyPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyPojo>> call, Response<List<CompanyPojo>> response) {
                // TODO LUM: what happen if response is incorrect?
                List<Object> data = response.body().get(0).getData();
                Log.d("getCompanyGeneral", "Successfull " + data.get(0) + " " + data.get(1) + " " + data.get(2));
            }

            @Override
            public void onFailure(Call<List<CompanyPojo>> call, Throwable t) {
                Log.d("getCompanyGeneral", "FAILED");
            }
        });
    }

    public static void getCompanyPrices(String ticker) {
        // TODO LUM: Path is incorrect
        StockBrainService service = RetrofitClientInstance.getRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyPojo>> call = service.getCompanyPrices(ticker);
        call.enqueue(new Callback<List<CompanyPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyPojo>> call, Response<List<CompanyPojo>> response) {
                // TODO LUM: what happen if response is incorrect?
                List<Object> data = response.body().get(0).getData();
                Log.d("getCompanyPrices", "Successfull " + data.get(0).toString() + " " + data.get(1) + " " + data.get(2));
            }

            @Override
            public void onFailure(Call<List<CompanyPojo>> call, Throwable t) {
                Log.d("getCompanyPrices", "FAILED");
            }
        });
    }

    public static void getCompanyStatements(String ticker) {
        // TODO LUM: Path is incorrect
        StockBrainService service = RetrofitClientInstance.getRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyStatementsPojo>> call = service.getCompanyStatements(ticker, RestConstants.STATEMENT, RestConstants.PERIOD, RestConstants.FYEAR);
        call.enqueue(new Callback<List<CompanyStatementsPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyStatementsPojo>> call, Response<List<CompanyStatementsPojo>> response) {
                // TODO LUM: what happen if response is incorrect?
                List<Object> data = response.body().get(0).getData();
                String currency = response.body().get(0).getCurrency();
                Log.d("getCompanyStatements", "Successfull " + data.get(0) + " " + data.get(1) + " " + data.get(2) + " " + currency);
            }

            @Override
            public void onFailure(Call<List<CompanyStatementsPojo>> call, Throwable t) {
                Log.d("getCompanyStatements", "FAILED");
            }
        });
    }
}