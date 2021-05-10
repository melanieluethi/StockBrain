package com.example.stockbrain.viewmodel;

import com.example.stockbrain.model.businessobject.SecurityItem;

import java.util.List;

public interface ListItemInteractionInterface {
    List<SecurityItem> getCompanyList();
    void getCompanyDetails(String ticker);
    boolean createCompany(String ticker);
    void deleteCompany(String ticker);
}