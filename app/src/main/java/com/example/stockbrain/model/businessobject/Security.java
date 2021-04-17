package com.example.stockbrain.model.businessobject;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

public class Security {
    private String tickerSymbol;
    private String name;
    private Image logo;

    public Security(String tickerSymbol, String name, Image logo) {
        this.tickerSymbol = tickerSymbol;
        this.name = name;
        this.logo = logo;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }
}