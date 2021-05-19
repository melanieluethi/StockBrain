package com.example.stockbrain.viewmodel;

import com.example.stockbrain.model.businessobject.DailyPrice;
import com.example.stockbrain.model.businessobject.FundamentalData;
import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.database.RepositoryProvider;

public class AllCompanyDetails {
    private SecurityItem securityItem;
    private FundamentalData fundamentalData;
    private DailyPrice dailyPrice;

    public AllCompanyDetails(String ticker) {
        this.securityItem = RepositoryProvider.getSecurityItemRepositoryInstance().getByTicker(ticker);
        this.fundamentalData = (FundamentalData) RepositoryProvider.getFundamentalDataRepositoryInstance().getByTicker(ticker);
        this.dailyPrice = (DailyPrice) RepositoryProvider.getDailyPriceRepositoryInstance().getByTicker(ticker);
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