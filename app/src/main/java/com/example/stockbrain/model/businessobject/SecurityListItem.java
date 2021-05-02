package com.example.stockbrain.model.businessobject;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;


@Table(name ="SecurityListItem")
public class SecurityListItem extends Model {

    @Column(name="itemState")
    private boolean itemState; //when true, item is selected

    @Column(name="securityItem") // notNull = true, onNullConflict = TableInfo.Column.ConflictAction.FAIL)
    private SecurityItem securityItem;

    @Column(name="securityList") // notNull = true, onNullConflict = TableInfo.Column.ConflictAction.FAIL)
    private SecurityList securityList;

    public SecurityListItem(){}


    public boolean isItemState() {
        return itemState;
    }

    public void setItemState(boolean itemState) {
        this.itemState = itemState;
    }


    public SecurityItem getSecurityItem() {
        return securityItem;
    }

    public void setSecurityItem(SecurityItem securityItem) {
        this.securityItem = securityItem;
    }

    public SecurityList getSecurityList() {
        return securityList;
    }

    public void setSecurityList(SecurityList securityList) {
        this.securityList = securityList;
    }

    @Override
    public String toString() {
        return securityList + " -> ID:" + getId() + "," + this.getSecurityItem();
    }

    public JSONObject toJson(){
        try {
            JSONObject json = new JSONObject();
            json.put("id", getId());
            json.put("securityItem", securityItem.getId());
            json.put("securityList", securityList.getId());
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}