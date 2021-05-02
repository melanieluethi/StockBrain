package com.example.stockbrain.viewmodel;

import android.view.View;

import androidx.databinding.BaseObservable;

import com.example.stockbrain.model.businessobject.Security;

public class CompanyDetailsAdapter extends BaseObservable implements ListItemInteractionInterface {
    private String result;
    private Security security;

    @Override
    public void onItemClick(View view, int position) {

    }

    public void getCompanyDetails(String ticker) {
        // TODO LUM: handling when which methode is called and convert to dao
        CompanyDetailsGetAdapter.getCompanyGeneral(ticker);
        CompanyDetailsGetAdapter.getCompanyPrices(ticker);
        CompanyDetailsGetAdapter.getCompanyStatements(ticker);
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