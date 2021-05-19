package com.example.stockbrain.model.database;

import com.activeandroid.Model;

public abstract class AbstractRepository {
    /**
     * Saves or updates the given database entity
     */
    public void saveEntity(Model model){
        model.save();
    }

    /**
     * Deletes the given database entity
     */
    public void deleteEntity(Model model){
        model.delete();
    }

    public abstract Model getByTicker(String tickerSymbol);
}