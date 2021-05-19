package com.example.stockbrain.viewmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.stockbrain.model.businessobject.DailyPrice;
import com.example.stockbrain.model.businessobject.FundamentalData;
import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.database.RepositoryProvider;
import com.example.stockbrain.model.database.SecurityItemRepository;
import com.example.stockbrain.view.MainActivity;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CompanyAdapter implements CompanyAdapterInterface {
    private ArrayList<SecurityItem> securityItemList = new ArrayList<>();
    private SecurityItemRepository securityItemRepositoryInstance = RepositoryProvider.getSecurityItemRepositoryInstance();
    private MainActivity mainActivity;

    public CompanyAdapter() {
        securityItemList.addAll(securityItemRepositoryInstance.getAllItems());
    }

    public CompanyAdapter(MainActivity mainActivity) {
        securityItemList.addAll(securityItemRepositoryInstance.getAllItems());
        this.mainActivity = mainActivity;
    }

    public void createCompany(String ticker) {
        SecurityItem securityItem = securityItemRepositoryInstance.getByTicker(ticker);
        if (securityItem == null) {
            GeneralCompanyDataAdapter generalCompanyAdapter = new GeneralCompanyDataAdapter(this);
            generalCompanyAdapter.getCompanyGeneral(ticker);
        }
    }

    @Override
    public void deleteCompany(String ticker) {
        deleteCompanyDetails(ticker);
        SecurityItem securityItem = securityItemRepositoryInstance.getByTicker(ticker);
        if (securityItem != null) {
            securityItemRepositoryInstance.deleteEntity(securityItem);
            securityItemList.remove(securityItem);
        }
    }

    private void deleteCompanyDetails(String ticker) {
        FundamentalData fundamentalData = (FundamentalData) RepositoryProvider.getFundamentalDataRepositoryInstance().getByTicker(ticker);
        if (fundamentalData != null)
            securityItemRepositoryInstance.deleteEntity(fundamentalData);

        DailyPrice dailyPrice = (DailyPrice) RepositoryProvider.getDailyPriceRepositoryInstance().getByTicker(ticker);
        if (dailyPrice != null)
            securityItemRepositoryInstance.deleteEntity(dailyPrice);
    }

    public ArrayList<SecurityItem> getCompanyList() {
        return securityItemList;
    }

    public void addCompanyList(SecurityItem securityItem) {
        this.securityItemList.add(securityItem);
    }

    public void getCompanyDetails(String ticker) {
        CompanyDetailsDataAdapter companyDetailsGetAdapter = new CompanyDetailsDataAdapter(this);
        companyDetailsGetAdapter.getCompanyDetails(ticker);
    }

    protected void buildMainActivityList() {
        mainActivity.buildList();
    }

    protected void messageSuccessfully(String successfully) {
        mainActivity.messageSuccessfully(successfully);
    }

    protected void messageWentWrong(String error) {
        mainActivity.messageWentWrong(error);
    }

    protected void sendData(String ticker) {
        mainActivity.sendData(mainActivity.getMainView(), mainActivity.getCompanyPicked(), ticker);
    }
}