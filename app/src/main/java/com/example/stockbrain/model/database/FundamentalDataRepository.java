package com.example.stockbrain.model.database;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.example.stockbrain.model.businessobject.FundamentalData;

public class FundamentalDataRepository extends AbstractRepository {
    @Override
    public Model getByTicker(String tickerSymbol) {
        return new Select().from(FundamentalData.class).where("TickerSymbol=?", new Object[]{tickerSymbol}).executeSingle();
    }
}