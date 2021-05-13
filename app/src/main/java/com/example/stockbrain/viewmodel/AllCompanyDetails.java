package com.example.stockbrain.viewmodel;

import com.example.stockbrain.model.businessobject.DailyPrice;
import com.example.stockbrain.model.businessobject.FundamentalData;
import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.database.RepositoryProvider;

public class AllCompanyDetails {
    private SecurityItem securityItem;
    private FundamentalData fundamentalData;
    private DailyPrice dailyPrice;

    public AllCompanyDetails getAllCompanyDetails(String ticker) {
        this.securityItem = RepositoryProvider.getSecurityItemRepositoryInstance().getByTicker(ticker);
        this.fundamentalData = RepositoryProvider.getFundamentalDataRepository().getByTicker(ticker);
        this.dailyPrice = RepositoryProvider.getDailyPriceRepository().getByTicker(ticker);
        return this;
    }
}