package com.example.stockbrain.model.database;

import com.activeandroid.Model;

import java.util.List;

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

    /**
     * To be implemented by subclass. Thought to return all elements
     * @return
     */
    public abstract List<? extends Model> getAllItems();

    public abstract  Model getByTicker (String tickerSymbol);
}