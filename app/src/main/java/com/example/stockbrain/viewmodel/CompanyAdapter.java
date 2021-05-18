package com.example.stockbrain.viewmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.stockbrain.model.businessobject.DailyPrice;
import com.example.stockbrain.model.businessobject.FundamentalData;
import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.database.RepositoryProvider;
import com.example.stockbrain.model.database.StockBrainRepository;
import com.example.stockbrain.view.MainActivity;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CompanyAdapter implements CompanyAdapterInterface {
    private ArrayList<SecurityItem> securityItemList = new ArrayList<>();
    private StockBrainRepository stockBrainRepositoryInstance = RepositoryProvider.getStockBrainRepositoryInstance();
    private MainActivity mainActivity;

    public CompanyAdapter() {
        securityItemList.addAll(stockBrainRepositoryInstance.getAllItems());
    }

    public CompanyAdapter(MainActivity mainActivity) {
        securityItemList.addAll(stockBrainRepositoryInstance.getAllItems());
        this.mainActivity = mainActivity;
    }

    public void createCompany(String ticker) {
        SecurityItem securityItem = (SecurityItem) stockBrainRepositoryInstance.getByTicker(SecurityItem.class, ticker);
        if (securityItem == null) {
            GeneralCompanyDataAdapter generalCompanyAdapter = new GeneralCompanyDataAdapter(this);
            generalCompanyAdapter.getCompanyGeneral(ticker);
        }
    }

    @Override
    public void deleteCompany(String ticker) {
        deleteCompanyDetails(ticker);
        SecurityItem securityItem = (SecurityItem) stockBrainRepositoryInstance.getByTicker(SecurityItem.class, ticker);
        if (securityItem != null) {
            stockBrainRepositoryInstance.deleteEntity(securityItem);
            securityItemList.remove(securityItem);
        }
    }

    private void deleteCompanyDetails(String ticker) {
        FundamentalData fundamentalData = (FundamentalData) stockBrainRepositoryInstance.getByTicker(FundamentalData.class, ticker);
        if (fundamentalData != null)
            stockBrainRepositoryInstance.deleteEntity(fundamentalData);

        DailyPrice dailyPrice = (DailyPrice) stockBrainRepositoryInstance.getByTicker(DailyPrice.class, ticker);
        if (dailyPrice != null)
            stockBrainRepositoryInstance.deleteEntity(dailyPrice);
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

    protected void messageWentWrong(String error) {
        mainActivity.messageWentWrong(error);
    }

    protected void sendData(String ticker) {
        mainActivity.sendData(mainActivity.getMainView(), mainActivity.getCompanyPicked(), ticker);
    }
}