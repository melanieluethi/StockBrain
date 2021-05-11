package com.example.stockbrain.model.database;

import android.os.Build;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.N)
public class RepositoryProvider {
    private static SecurityItemRepository securityItemRepository;
    private static FundamentalDataRepository fundamentalDataRepository;
    private static DailyPriceRepository dailyPriceRepository;
    private static SecurityListItemRepository securityListItemRepository;
    private static SecurityListRepository securityListRepository;

    public static SecurityItemRepository getSecurityItemRepositoryInstance(){
        if(securityItemRepository == null){
            securityItemRepository = new SecurityItemRepository();
        }
        return securityItemRepository;
    }

    public static SecurityListItemRepository getSecurityListItemRepositoryInstance(){
        if(securityListItemRepository == null){
            securityListItemRepository = new SecurityListItemRepository();
        }
        return securityListItemRepository;
    }


    public static SecurityListRepository getSecurityListRepositoryInstance(){
        if(securityListRepository == null){
            securityListRepository = new SecurityListRepository();
        }
        return securityListRepository;
    }

    public static FundamentalDataRepository getFundamentalDataRepository() {
        if(fundamentalDataRepository == null){
            fundamentalDataRepository = new FundamentalDataRepository();
        }
        return fundamentalDataRepository;
    }

    public static DailyPriceRepository getDailyPriceRepository() {
        if(dailyPriceRepository == null){
            dailyPriceRepository = new DailyPriceRepository();
        }
        return dailyPriceRepository;
    }
}