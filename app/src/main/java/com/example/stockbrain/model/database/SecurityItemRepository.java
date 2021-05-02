package com.example.stockbrain.model.database;

import com.activeandroid.query.Select;
import com.example.stockbrain.model.businessobject.SecurityItem;

import java.util.List;

public class SecurityItemRepository extends AbstractRepository {

    protected SecurityItemRepository(){}

    @Override
    public List<SecurityItem> getAllItems() {
        return new Select().from(SecurityItem.class).execute();
    }


    /**
     * Returns only one shopping item with provided id.
     * Returns null if tupel with given id does not exist
     */
    public SecurityItem getShoppingItemById(long id){
        return (SecurityItem) super.getById(SecurityItem.class, id);
    }

    public SecurityItem getByName(String name) {
        return new Select().from(SecurityItem.class).where("itemName=?", new Object[]{name}).executeSingle();
    }
}
