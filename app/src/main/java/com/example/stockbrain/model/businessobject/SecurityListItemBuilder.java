package com.example.stockbrain.model.businessobject;

public class SecurityListItemBuilder {

    private boolean itemState; //when true, item is selected
    private SecurityItem securityItem;
    private SecurityList securityList;

    public SecurityListItemBuilder(){

    }

    public SecurityListItemBuilder withItemState(boolean itemState){
        this.itemState = itemState;
        return this;
    }

    public SecurityListItemBuilder withSecurityItem(SecurityItem securityItem){
        this.securityItem = securityItem;
        return this;
    }

    public SecurityListItemBuilder withSecurityList(SecurityList securityList){
        this.securityList = securityList;
        return this;
    }

    public SecurityListItem build(){
        SecurityListItem securityListItem = new SecurityListItem();
        securityListItem.setItemState(this.itemState);
        securityListItem.setSecurityItem(this.securityItem);
        securityListItem.setSecurityList(this.securityList);
        return securityListItem;
    }

}
