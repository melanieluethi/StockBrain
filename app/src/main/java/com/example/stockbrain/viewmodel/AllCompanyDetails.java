package com.example.stockbrain.viewmodel;

import android.util.Log;

import com.example.stockbrain.model.businessobject.DailyPrice;
import com.example.stockbrain.model.businessobject.FundamentalData;
import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.database.RepositoryProvider;
import com.example.stockbrain.model.database.StockBrainRepository;

public class AllCompanyDetails {
    private SecurityItem securityItem;
    private FundamentalData fundamentalData;
    private DailyPrice dailyPrice;

    public AllCompanyDetails(String ticker) {
        StockBrainRepository stockBrainRepositoryInstance = RepositoryProvider.getStockBrainRepositoryInstance();
        Log.d("getAllCompanyDetails", "has started");
        this.securityItem = (SecurityItem) stockBrainRepositoryInstance.getByTicker(SecurityItem.class, ticker);
        Log.d("AllCompanyDetails - securityItem", securityItem.getTickerSymbol());
        this.fundamentalData = (FundamentalData) stockBrainRepositoryInstance.getByTicker(FundamentalData.class, ticker);
        Log.d("AllCompanyDetails - fundamentalData", fundamentalData.getTickerSymbol());
        this.dailyPrice = (DailyPrice) stockBrainRepositoryInstance.getByTicker(DailyPrice.class, ticker);
        Log.d("AllCompanyDetails - dailyPrice", dailyPrice.getTickerSymbol());
        Log.d("getAllCompanyDetails", "has ended");
    }

    public SecurityItem getSecurityItem() {
        return securityItem;
    }

    public FundamentalData getFundamentalData() {
        return fundamentalData;
    }

    public DailyPrice getDailyPrice() {
        return dailyPrice;
    }
}