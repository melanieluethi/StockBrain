package com.example.stockbrain.model.businessobject;

import android.media.Image;

public class SecurityListBuilder {
    private String tickerSymbol;
    private String name;
    private Image logo;

    public SecurityListBuilder withTickerSymbol(String tickerSymbol){
        this.tickerSymbol = tickerSymbol;
        return this;
    }

    public SecurityListBuilder withName(String name){
        this.name = name;
        return this;
    }

    public SecurityListBuilder withLogo(Image logo){
        this.logo = logo;
        return this;
    }

    public Security build(){
        Security security = new Security(this.tickerSymbol, this.name, this.logo);
        return security;
    }
}
