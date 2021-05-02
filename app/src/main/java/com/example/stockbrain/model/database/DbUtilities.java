package com.example.stockbrain.model.database;

import com.activeandroid.Model;
import com.activeandroid.query.Select;

public class DbUtilities {

    public static boolean isEmpty(Class<? extends Model> clazz){
        return new Select().from(clazz).count() <= 0 ? true : false;
    }
}
