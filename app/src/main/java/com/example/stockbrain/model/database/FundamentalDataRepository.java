package com.example.stockbrain.model.database;

import com.activeandroid.query.Select;
import com.example.stockbrain.model.businessobject.FundamentalData;

import java.util.List;

public class FundamentalDataRepository extends AbstractRepository {
    @Override
    public List<FundamentalData> getAllItems() {
        return new Select().from(FundamentalData.class).execute();
    }

    @Override
    public FundamentalData getByTicker(String ticker) {
        return new Select().from(FundamentalData.class).where("TickerSymbol=?", new Object[]{ticker}).executeSingle();
    }
}
