package com.example.stockbrain.model.businessobject;

import android.media.Image;

public class SecurityItemBuilder {

    private String tickerSymbol;
    private Image logo;
    private String name;

    public SecurityItemBuilder(){
        //set default values
        this.tickerSymbol = tickerSymbol;
        this.name = "";
        this.logo = logo;
    }

    public SecurityItemBuilder withItemName(String name){
        this.name = name;
        return this;
    }

    public SecurityItemBuilder withTickerSymbol(String tickerSymbol){
        this.tickerSymbol = tickerSymbol;
        return this;
    }
    public SecurityItem build(){
        SecurityItem securityItem = new SecurityItem();
        securityItem.setTickerSymbol(this.tickerSymbol);
        securityItem.setName(this.name);
        securityItem.setLogo(this.logo);

        return securityItem;
    }
}
