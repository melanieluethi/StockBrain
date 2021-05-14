package com.example.stockbrain.model.database;

import com.activeandroid.query.Select;
import com.example.stockbrain.model.businessobject.DailyPrice;

import java.util.List;

public class DailyPriceRepository extends AbstractRepository {
    @Override
    public List<DailyPrice> getAllItems() {
        return new Select().from(DailyPrice.class).execute();
    }

    @Override
    public DailyPrice getByTicker(String ticker) {
        return new Select().from(DailyPrice.class).where("TickerSymbol=?", new Object[]{ticker}).executeSingle();
    }
}