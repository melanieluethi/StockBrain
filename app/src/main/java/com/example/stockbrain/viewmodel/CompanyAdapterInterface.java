package com.example.stockbrain.viewmodel;

import com.example.stockbrain.model.businessobject.SecurityItem;

import java.util.ArrayList;

public interface CompanyAdapterInterface {
    ArrayList<SecurityItem> getCompanyList();
    void getCompanyDetails(String ticker);
    void createCompany(String ticker);
    void deleteCompany(String ticker);
}