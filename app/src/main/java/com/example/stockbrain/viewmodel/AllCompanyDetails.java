package com.example.stockbrain.viewmodel;

import com.example.stockbrain.model.businessobject.DailyPrice;
import com.example.stockbrain.model.businessobject.FundamentalData;
import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.database.RepositoryProvider;
import com.example.stockbrain.model.database.StockBrainRepository;

public class AllCompanyDetails {
    private SecurityItem securityItem;
    private FundamentalData fundamentalData;
    private DailyPrice dailyPrice;

    public SecurityItem getSecurityItem() {
        return securityItem;
    }

    public FundamentalData getFundamentalData() {
        return fundamentalData;
    }

    public DailyPrice getDailyPrice() {
        return dailyPrice;
    }

    public AllCompanyDetails getAllCompanyDetails(String ticker) {
        StockBrainRepository stockBrainRepositoryInstance = RepositoryProvider.getStockBrainRepositoryInstance();
        this.securityItem = (SecurityItem) stockBrainRepositoryInstance.getByTicker(SecurityItem.class, ticker);
        this.fundamentalData = (FundamentalData) stockBrainRepositoryInstance.getByTicker(FundamentalData.class, ticker);
        this.dailyPrice = (DailyPrice) stockBrainRepositoryInstance.getByTicker(DailyPrice.class, ticker);
        return this;
    }
}