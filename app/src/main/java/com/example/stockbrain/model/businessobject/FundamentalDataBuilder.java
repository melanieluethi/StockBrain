package com.example.stockbrain.model.businessobject;

public class FundamentalDataBuilder {
    private String tickerSymbol;
    private Double revenue;
    private Double profit;
    private Double assets;
    private Double liabilities;

    public FundamentalDataBuilder() {
        this.tickerSymbol = "";
        this.revenue = 0.0;
        this.profit = 0.0;
        this.assets = 0.0;
        this.liabilities = 0.0;
    }

    public FundamentalDataBuilder withTickerSymbol(String tickerSymbol){
        this.tickerSymbol = tickerSymbol;
        return this;
    }

    public FundamentalDataBuilder withRevenue(Double revenue){
        this.revenue = revenue;
        return this;
    }

    public FundamentalDataBuilder withProfit(Double profit){
        this.profit = profit;
        return this;
    }

    public FundamentalDataBuilder withAssets(Double assets){
        this.assets = assets;
        return this;
    }

    public FundamentalDataBuilder withLiabilities(Double liabilities){
        this.liabilities = liabilities;
        return this;
    }

    public FundamentalData build(){
        FundamentalData fundamentalData = new FundamentalData();
        fundamentalData.setTickerSymbol(this.tickerSymbol);
        fundamentalData.setRevenue(this.revenue);
        fundamentalData.setProfit(this.profit);
        fundamentalData.setAssets(this.assets);
        fundamentalData.setLiabilities(this.liabilities);
        return fundamentalData;
    }
}