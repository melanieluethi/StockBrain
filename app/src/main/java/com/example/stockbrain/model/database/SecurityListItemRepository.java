package com.example.stockbrain.model.database;

import com.activeandroid.query.Select;
import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.businessobject.SecurityList;
import com.example.stockbrain.model.businessobject.SecurityListItem;

import java.util.List;

public class SecurityListItemRepository extends AbstractRepository{

    protected SecurityListItemRepository(){}
    @Override
    public List<SecurityListItem> getAllItems() {
        return new Select().from(SecurityListItem.class).execute();
    }

    /**
     * Returns only one  item with provided id.
     * Returns null if tupel with given id does not exist
     */
    public SecurityListItem getSecurityListById(long id){
        return (SecurityListItem) super.getById(SecurityListItem.class, id);
    }

    /**
     * Returns all assigned  items in given List
     */
    public List<SecurityItem> getSecurityItems(SecurityList securityList){
        return new Select().from(SecurityItem.class)
                .join(SecurityListItem.class).on("SecurityItem.Id = SecurityListItem.securityItem")
                .where("SecurityListItem.securityList=?", new Object[]{securityList.getId()})
                .execute();
    }

    /**
     * Returns all  list Items with the given Item
     */
    public List<SecurityListItem> getSecurityListItems(SecurityItem securityItem) {
        return new Select().from(SecurityListItem.class)
                .where("SecurityListItem.securityItem=?", new Object[]{securityItem.getTickerSymbol()})
                .execute();
    }
}
