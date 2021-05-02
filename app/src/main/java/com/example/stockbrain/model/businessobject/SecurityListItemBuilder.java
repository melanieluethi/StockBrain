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

    public SecurityListItemBuilder withShoppingItem(SecurityItem shoppingItem){
        this.securityItem = securityItem;
        return this;
    }

    public SecurityListItemBuilder withShoppingList(SecurityList shoppingList){
        this.securityList = securityList;
        return this;
    }

    public SecurityListItem build(){
        SecurityListItem shoppingListItem = new SecurityListItem();
        shoppingListItem.setItemState(this.itemState);
        shoppingListItem.setSecurityItem(this.securityItem);
        shoppingListItem.setSecurityList(this.securityList);
        return shoppingListItem;
    }

}
