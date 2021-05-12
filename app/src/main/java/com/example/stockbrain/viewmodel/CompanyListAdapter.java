package com.example.stockbrain.viewmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;

import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.database.RepositoryProvider;
import com.example.stockbrain.model.database.SecurityItemRepository;

import java.util.List;

public class CompanyListAdapter extends BaseObservable implements ListItemInteractionInterface {

    public void createCompany(String ticker) {
        CompanyDetailsGetAdapter companyDetailsGetAdapter = new CompanyDetailsGetAdapter();
        companyDetailsGetAdapter.getCompanyGeneral(ticker);
    }

    @Override
    public void deleteCompany(String ticker) {
        SecurityItemRepository securityItemRepository = RepositoryProvider.getSecurityItemRepositoryInstance();
        SecurityItem securityItem = securityItemRepository.getByTicker(ticker);
        securityItemRepository.deleteEntity(securityItem);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<SecurityItem> getCompanyList() {
        return RepositoryProvider.getSecurityItemRepositoryInstance().getAllItems();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getCompanyDetails(String ticker) {
        // TODO LUM: handling when which methode is called and get Data out of database
        CompanyDetailsGetAdapter companyDetailsGetAdapter = new CompanyDetailsGetAdapter();
        companyDetailsGetAdapter.getCompanyPrices(ticker);
        companyDetailsGetAdapter.getCompanyFundamentalData(ticker);
    }
}