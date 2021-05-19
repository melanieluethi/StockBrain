package com.example.stockbrain.model.database;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.example.stockbrain.model.businessobject.DailyPrice;

public class DailyPriceRepository extends AbstractRepository {
    @Override
    public Model getByTicker(String tickerSymbol) {
        return new Select().from(DailyPrice.class).where("TickerSymbol=?", new Object[]{tickerSymbol}).executeSingle();
    }
}