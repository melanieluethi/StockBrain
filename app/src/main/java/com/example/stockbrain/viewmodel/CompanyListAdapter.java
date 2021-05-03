package com.example.stockbrain.viewmodel;

import android.view.View;

import androidx.databinding.BaseObservable;

public class CompanyListAdapter extends BaseObservable implements ListItemInteractionInterface {

    @Override
    public void onItemClick(View view, int position) {

    }

    public void getCompanyDetails(String ticker) {
        // TODO LUM: handling when which methode is called and convert to dao
        CompanyDetailsGetAdapter.getCompanyGeneral(ticker);
        CompanyDetailsGetAdapter.getCompanyPrices(ticker);
        CompanyDetailsGetAdapter.getCompanyStatements(ticker);
    }

}