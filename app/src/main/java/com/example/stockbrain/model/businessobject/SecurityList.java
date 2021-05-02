package com.example.stockbrain.model.businessobject;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SecurityList extends Model implements Comparable<SecurityList> {

    @Column(name="listName")
    private String listName;

    private List<SecurityListItem> securityListItems;

    public SecurityList(){}

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<SecurityListItem> getSecurityListItems() {
        return getSecurityListItemsFromDB(false);
    }

    public SecurityListItem getShoppingListItem(SecurityItem securityItem) {
        for (SecurityListItem it:this.getSecurityListItems())
            if(it.getSecurityItem().equals(securityItem))
                return it;
        return null;
    }

    public void setSecurityListItems(List<SecurityListItem> securityListItems) {
        this.securityListItems = securityListItems;
    }



    private List<SecurityListItem> getSecurityListItemsFromDB(boolean bypassCache){
        if(this.securityListItems == null || bypassCache){
//            this.securityListItems = RepositoryProvider.getSecurityListRepositoryInstance().getSecurityListItems(this);
        }
        return this.securityListItems;
    }

    @Override
    public String toString() {
        return "ShoppingList{ID: " + getId() +
                ",listName='" + listName + '\'' +
                '}';
    }

    @Override
    public int compareTo(SecurityList o) {
        return this.getListName().compareTo(o.getListName());
    }

    public JSONObject toJson(){
        try {
            JSONObject json = new JSONObject();
            json.put("id", getId());
            json.put("listName", listName);
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}

