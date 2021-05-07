package com.example.stockbrain.viewmodel;

import android.os.Build;

import androidx.databinding.BaseObservable;

import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.database.RepositoryProvider;

import java.util.ArrayList;
import java.util.List;

public class CompanyListAdapter extends BaseObservable implements ListItemInteractionInterface {
    private CompanyDetailsGetAdapter companyDetailsGetAdapter = new CompanyDetailsGetAdapter();

    public boolean createCompany(String ticker) {
        return false;
    }

    @Override
    public boolean deleteCompany(String ticker) {
        return false;
    }

    public List<SecurityItem> getCompanyList() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return RepositoryProvider.getSecurityItemRepositoryInstance().getAllItems();
        }
        return new ArrayList<SecurityItem>();
    }

    public void getCompanyDetails(String ticker) {
        // TODO LUM: handling when which methode is called and convert to dao

        companyDetailsGetAdapter.getCompanyGeneral(ticker);
        companyDetailsGetAdapter.getCompanyPrices(ticker);
        companyDetailsGetAdapter.getCompanyStatements(ticker);
    }
}