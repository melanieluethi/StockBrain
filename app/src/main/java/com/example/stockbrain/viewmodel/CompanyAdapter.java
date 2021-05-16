package com.example.stockbrain.viewmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.stockbrain.model.businessobject.DailyPrice;
import com.example.stockbrain.model.businessobject.FundamentalData;
import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.database.DailyPriceRepository;
import com.example.stockbrain.model.database.FundamentalDataRepository;
import com.example.stockbrain.model.database.RepositoryProvider;
import com.example.stockbrain.model.database.SecurityItemRepository;
import com.example.stockbrain.view.MainActivity;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CompanyAdapter implements CompanyAdapterInterface {
    private ArrayList<SecurityItem> securityItemList = new ArrayList<>();
    private MainActivity mainActivity;

    public CompanyAdapter() {
        securityItemList.addAll(RepositoryProvider.getSecurityItemRepositoryInstance().getAllItems());
    }

    public CompanyAdapter(MainActivity mainActivity) {
        securityItemList.addAll(RepositoryProvider.getSecurityItemRepositoryInstance().getAllItems());
        this.mainActivity = mainActivity;
    }

    public void createCompany(String ticker) {
        SecurityItem securityItem = RepositoryProvider.getSecurityItemRepositoryInstance().getByTicker(ticker);
        if (securityItem == null) {
            GeneralCompanyDataAdapter generalCompanyAdapter = new GeneralCompanyDataAdapter(this);
            generalCompanyAdapter.getCompanyGeneral(ticker);
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
        FundamentalDataRepository fundamentalDataRepository = RepositoryProvider.getFundamentalDataRepositoryInstance();
        FundamentalData fundamentalData = fundamentalDataRepository.getByTicker(ticker);
        if (fundamentalData != null)
            fundamentalDataRepository.deleteEntity(fundamentalData);

        DailyPriceRepository dailyPriceRepository = RepositoryProvider.getDailyPriceRepositoryInstance();
        DailyPrice dailyPrice = dailyPriceRepository.getByTicker(ticker);
        if (dailyPrice != null)
            dailyPriceRepository.deleteEntity(dailyPrice);
    }

    public ArrayList<SecurityItem> getCompanyList() {
        return securityItemList;
    }

    public void addCompanyList(SecurityItem securityItem) {
        this.securityItemList.add(securityItem);
    }

    public AllCompanyDetails getAllCompanyDetails(String ticker) {
        getCompanyDetails(ticker);
        AllCompanyDetails allCompanyDetails = new AllCompanyDetails();
        return allCompanyDetails.getAllCompanyDetails(ticker);
    }

    private void getCompanyDetails(String ticker) {
        CompanyDetailsDataAdapter companyDetailsGetAdapter = new CompanyDetailsDataAdapter();
        companyDetailsGetAdapter.getCompanyPrices(ticker);
        companyDetailsGetAdapter.getCompanyFundamentalData(ticker);
    }

    protected void buildMainActivityList() {
        mainActivity.buildList();
    }
}