package com.example.stockbrain.model.rest.pojo;

import com.google.gson.annotations.SerializedName;

public class CompanyPricesPojo {
    @SerializedName("SimFinId")
    private String simFinId;

    @SerializedName("Ticker")
    private String ticker;

    @SerializedName("Company Name")
    private String companyName;

    @SerializedName("IndustryId")
    private String industryId;

    @SerializedName("Month FY End")
    private String monthFYEnd;

    @SerializedName("Number Employees")
    private String numberEmployees;

    @SerializedName("Business Summary")
    private String businessSummary;
}
