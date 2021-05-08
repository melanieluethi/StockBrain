package com.example.stockbrain.viewmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;

import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.database.RepositoryProvider;

import java.util.List;

public class CompanyListAdapter extends BaseObservable implements ListItemInteractionInterface {
    private CompanyDetailsGetAdapter companyDetailsGetAdapter = new CompanyDetailsGetAdapter();

    public boolean createCompany(String ticker) {
        companyDetailsGetAdapter.getCompanyGeneral(ticker);
//        SecurityItemRepository securityItemRepository = RepositoryProvider.getSecurityItemRepositoryInstance();
//        SecurityItem securityItem = securityItemRepository.getByTicker(ticker);
//        companyDetailsGetAdapter.getLogoUrl(ticker, securityItem.getName());

        return false;
    }

    @Override
    public boolean deleteCompany(String ticker) {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<SecurityItem> getCompanyList() {
        return RepositoryProvider.getSecurityItemRepositoryInstance().getAllItems();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getCompanyDetails(String ticker) {
        // TODO LUM: handling when which methode is called and convert to dao
        companyDetailsGetAdapter.getCompanyPrices(ticker);
        companyDetailsGetAdapter.getCompanyStatements(ticker);
    }
}