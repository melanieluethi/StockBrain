package com.example.stockbrain.model.rest.pojo;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class CompanyPojo {
    @SerializedName("SimFinId")
    private Integer simFinId;

    @SerializedName("Ticker")
    private String ticker;

    @SerializedName("Company Name")
    private String companyName;

    @SerializedName("IndustryId")
    private Integer industryId;

    @SerializedName("Month FY End")
    private Integer monthFYEnd;

    @SerializedName("Number Employees")
    private Integer numberEmployees;

    @SerializedName("Business Summary")
    private String businessSummary;

    public CompanyPojo(Integer simFinId, String ticker, String companyName, Integer industryId, Integer monthFYEnd, Integer numberEmployees, String businessSummary) {
        this.simFinId = simFinId;
        this.ticker = ticker;
        this.companyName = companyName;
        this.industryId = industryId;
        this.monthFYEnd = monthFYEnd;
        this.numberEmployees = numberEmployees;
        this.businessSummary = businessSummary;
    }

    public Integer getSimFinId() {
        return simFinId;
    }

    public void setSimFinId(Integer simFinId) {
        this.simFinId = simFinId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public Integer getMonthFYEnd() {
        return monthFYEnd;
    }

    public void setMonthFYEnd(Integer monthFYEnd) {
        this.monthFYEnd = monthFYEnd;
    }

    public Integer getNumberEmployees() {
        return numberEmployees;
    }

    public void setNumberEmployees(Integer numberEmployees) {
        this.numberEmployees = numberEmployees;
    }

    public String getBusinessSummary() {
        return businessSummary;
    }

    public void setBusinessSummary(String businessSummary) {
        this.businessSummary = businessSummary;
    }
}
