package com.example.stockbrain.model.database;

import com.activeandroid.query.Select;
import com.example.stockbrain.model.businessobject.SecurityItem;

import java.util.List;

public class StockBrainRepository extends AbstractRepository {
    @Override
    public List<SecurityItem> getAllItems() {
        return new Select().from(SecurityItem.class).execute();
    }
}