package com.example.stockbrain.model.database;

import android.os.Build;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.N)
public class RepositoryProvider {
    private static SecurityItemRepository securityItemRepository;
    private static FundamentalDataRepository fundamentalDataRepository;
    private static DailyPriceRepository dailyPriceRepository;

    public static SecurityItemRepository getSecurityItemRepositoryInstance(){
        if(securityItemRepository == null){
            securityItemRepository = new SecurityItemRepository();
        }
        return securityItemRepository;
    }

    public static FundamentalDataRepository getFundamentalDataRepositoryInstance(){
        if(fundamentalDataRepository == null){
            fundamentalDataRepository = new FundamentalDataRepository();
        }
        return fundamentalDataRepository;
    }

    public static DailyPriceRepository getDailyPriceRepositoryInstance(){
        if(dailyPriceRepository == null){
            dailyPriceRepository = new DailyPriceRepository();
        }
        return dailyPriceRepository;
    }
}