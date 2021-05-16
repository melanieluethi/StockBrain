package com.example.stockbrain.model.database;

import android.os.Build;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.N)
public class RepositoryProvider {
    private static StockBrainRepository stockBrainRepository;

    public static StockBrainRepository getStockBrainRepositoryInstance(){
        if(stockBrainRepository == null){
            stockBrainRepository = new StockBrainRepository();
        }
        return stockBrainRepository;
    }
}