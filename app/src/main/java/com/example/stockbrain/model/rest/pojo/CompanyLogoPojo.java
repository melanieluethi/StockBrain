package com.example.stockbrain.model.rest.pojo;

import com.google.gson.annotations.SerializedName;

public class CompanyLogoPojo {
    @SerializedName("name")
    private String name;
    @SerializedName("domain")
    private String domain;
    @SerializedName("logo")
    private String logo;

    public CompanyLogoPojo(String name, String domain, String logo) {
        this.name = name;
        this.domain = domain;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}