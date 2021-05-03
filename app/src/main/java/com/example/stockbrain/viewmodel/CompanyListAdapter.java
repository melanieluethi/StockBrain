package com.example.stockbrain.viewmodel;

import android.view.View;

import androidx.databinding.BaseObservable;

public class CompanyListAdapter extends BaseObservable implements ListItemInteractionInterface {
    private CompanyDetailsGetAdapter companyDetailsGetAdapter = new CompanyDetailsGetAdapter();

    @Override
    public void onItemClick(View view, int position) {

    }

    public void getCompanyDetails(String ticker) {
        // TODO LUM: handling when which methode is called and convert to dao

        companyDetailsGetAdapter.getCompanyGeneral(ticker);
        companyDetailsGetAdapter.getCompanyPrices(ticker);
        companyDetailsGetAdapter.getCompanyStatements(ticker);
    }

}