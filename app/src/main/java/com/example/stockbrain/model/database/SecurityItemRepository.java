package com.example.stockbrain.model.database;

import com.activeandroid.query.Select;
import com.example.stockbrain.model.businessobject.SecurityItem;

import java.util.List;

public class SecurityItemRepository extends AbstractRepository {
    public List<SecurityItem> getAllItems() {
        return new Select().from(SecurityItem.class).execute();
    }
    @Override
    public SecurityItem getByTicker(String tickerSymbol) {
        return new Select().from(SecurityItem.class).where("TickerSymbol=?", new Object[]{tickerSymbol}).executeSingle();
    }
}