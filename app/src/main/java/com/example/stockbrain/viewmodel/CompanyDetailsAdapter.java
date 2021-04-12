package com.example.stockbrain.viewmodel;

import android.view.View;

import androidx.databinding.BaseObservable;

import com.example.stockbrain.model.businessobject.Security;
import com.example.stockbrain.model.rest.service.StockBrainService;
import com.example.stockbrain.model.rest.util.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyDetailsAdapter extends BaseObservable implements ListItemInteractionInterface {
    private String result;
    private Security security;

    @Override
    public void onItemClick(View view, int position) {

    }

    public void getCompanyDetails(String symbol) {
        StockBrainService service = RetrofitClientInstance.getRetrofitInstance(symbol).create(StockBrainService.class);
        // TODO: LUM - change Security to Company details
        Call<Security> call = service.getCompanyDetail();
        call.enqueue(new Callback<Security>() {
            @Override
            public void onResponse(Call<Security> call, Response<Security> response) {
                result = "Success";
                //security = new Security(response.body().getTickerSymbol(), response.body().getName(), response.body().getLogo());
            }

            @Override
            public void onFailure(Call<Security> call, Throwable t) {
                // TODO: go to database and show old value

                result = "Something went wrong! You get old data.\n" + t.getMessage();
            }
        });
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }
}