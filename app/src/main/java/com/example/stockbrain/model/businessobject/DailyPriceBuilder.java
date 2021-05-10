package com.example.stockbrain.model.businessobject;

public class DailyPriceBuilder {
    private String tickerSymbol;
    private Double closingPrice; // aktueller Kurs
    private Integer volume; // Gehandeltes Volumen an Aktien

    public DailyPriceBuilder() {
        this.tickerSymbol = "";
        this.closingPrice = 0.0;
        this.volume = 0;
    }

    public DailyPriceBuilder withTickerSymbol(String tickerSymbol){
        this.tickerSymbol = tickerSymbol;
        return this;
    }

    public DailyPriceBuilder withClosingPrice(Double closingPrice){
        this.closingPrice = closingPrice;
        return this;
    }

    public DailyPriceBuilder withVolume(Integer volume){
        this.volume = volume;
        return this;
    }

    public DailyPrice build(){
        DailyPrice dailyPrice = new DailyPrice();
        dailyPrice.setTickerSymbol(this.tickerSymbol);
        dailyPrice.setClosingPrice(this.closingPrice);
        dailyPrice.setVolume(this.volume);
        return dailyPrice;
    }
}