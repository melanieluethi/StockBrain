package com.example.stockbrain.model.database;

import com.activeandroid.Model;
import com.activeandroid.query.Select;

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

    protected Model getByTicker(Class<? extends Model> entityClass, String tickerSymbol){
        return new Select().from(entityClass).where("TickerSymbol=?", new Object[]{tickerSymbol}).executeSingle();
    }

    /*
     * Returns a model with the given id in given class. This method is specified in subclasses!
     */
    protected Model getById(Class<? extends Model> entityClass, long id){
        return new Select().from(entityClass).where("Id=?", new Object[]{id}).executeSingle();
    }
}