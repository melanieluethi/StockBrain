package com.example.stockbrain.model.rest.pojo;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompanyPojo {
    @SerializedName("found")
    private boolean found;
    @SerializedName("columns")
    private List<String> columns;
    @SerializedName("data")
    private List<Object> data;

    public CompanyPojo(boolean found, List<String> columns, List<Object> data) {
        this.found = found;
        this.columns = columns;
        this.data = data;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
