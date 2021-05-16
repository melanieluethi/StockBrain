package com.example.stockbrain.viewmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableArrayList;

import com.example.stockbrain.model.businessobject.DailyPrice;
import com.example.stockbrain.model.businessobject.FundamentalData;
import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.database.DailyPriceRepository;
import com.example.stockbrain.model.database.FundamentalDataRepository;
import com.example.stockbrain.model.database.RepositoryProvider;
import com.example.stockbrain.model.database.SecurityItemRepository;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CompanyListAdapter extends BaseObservable implements ListItemInteractionInterface {
    private ObservableArrayList<SecurityItem> securityItemList = new ObservableArrayList<>();

    public CompanyListAdapter() {
        securityItemList.addAll(RepositoryProvider.getSecurityItemRepositoryInstance().getAllItems());
    }

    public void createCompany(String ticker) {
        SecurityItem securityItem = RepositoryProvider.getSecurityItemRepositoryInstance().getByTicker(ticker);
        if (securityItem == null) {
            CompanyDetailsGetAdapter companyDetailsGetAdapter = new CompanyDetailsGetAdapter(this);
            companyDetailsGetAdapter.getCompanyGeneral(ticker);
        }
    }

    @Override
    public void deleteCompany(String ticker) {
        deleteCompanyDetails(ticker);
        SecurityItemRepository securityItemRepository = RepositoryProvider.getSecurityItemRepositoryInstance();
        SecurityItem securityItem = securityItemRepository.getByTicker(ticker);
        if (securityItem != null) {
            securityItemRepository.deleteEntity(securityItem);
            securityItemList.remove(securityItem);
        }
    }

    private void deleteCompanyDetails(String ticker) {
        FundamentalDataRepository fundamentalDataRepository = RepositoryProvider.getFundamentalDataRepository();
        FundamentalData fundamentalData = fundamentalDataRepository.getByTicker(ticker);
        if (fundamentalData != null)
            fundamentalDataRepository.deleteEntity(fundamentalData);

        DailyPriceRepository dailyPriceRepository = RepositoryProvider.getDailyPriceRepository();
        DailyPrice dailyPrice = dailyPriceRepository.getByTicker(ticker);
        if (dailyPrice != null)
            dailyPriceRepository.deleteEntity(dailyPrice);
    }

    @Bindable
    public ObservableArrayList<SecurityItem> getCompanyList() {
        return securityItemList;
    }

    public void addCompanyList(SecurityItem securityItem) {
        this.securityItemList.add(securityItem);
    }
    public void removeCompanyList(SecurityItem securityItem) {
        this.securityItemList.remove(securityItem);
    }

    public AllCompanyDetails getAllCompanyDetails(String ticker) {
        getCompanyDetails(ticker);
        AllCompanyDetails allCompanyDetails = new AllCompanyDetails();
        return allCompanyDetails.getAllCompanyDetails(ticker);
    }

    private void getCompanyDetails(String ticker) {
        CompanyDetailsGetAdapter companyDetailsGetAdapter = new CompanyDetailsGetAdapter(this);
        companyDetailsGetAdapter.getCompanyPrices(ticker);
        companyDetailsGetAdapter.getCompanyFundamentalData(ticker);
    }
}