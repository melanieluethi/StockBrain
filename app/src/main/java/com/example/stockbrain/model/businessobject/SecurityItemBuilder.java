package com.example.stockbrain.model.businessobject;

public class SecurityItemBuilder {

    private String tickerSymbol;
    private String urlLogo;
    private String name;

    public SecurityItemBuilder() {
        //set default values
        this.tickerSymbol = "";
        this.name = "";
        this.urlLogo = "";
    }

    public SecurityItemBuilder withItemName(String name){
        this.name = name;
        return this;
    }

    public SecurityItemBuilder withTickerSymbol(String tickerSymbol){
        this.tickerSymbol = tickerSymbol;
        return this;
    }

    public SecurityItemBuilder withUrlLogo(String urlLogo){
        this.urlLogo = urlLogo;
        return this;
    }

    public SecurityItem build(){
        SecurityItem securityItem = new SecurityItem();
        securityItem.setTickerSymbol(this.tickerSymbol);
        securityItem.setName(this.name);
        securityItem.setUrlLogo(this.urlLogo);
        return securityItem;
    }
}
