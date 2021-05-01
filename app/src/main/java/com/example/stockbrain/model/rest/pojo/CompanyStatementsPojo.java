package com.example.stockbrain.model.rest.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompanyStatementsPojo extends CompanyPojo {
    @SerializedName("currency")
    private String currency;

    public CompanyStatementsPojo(boolean found, List<String> columns, List<Object> data, String currency) {
        super(found, columns, data);
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}