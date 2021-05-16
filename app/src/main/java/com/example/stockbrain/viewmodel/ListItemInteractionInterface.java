package com.example.stockbrain.viewmodel;

import androidx.databinding.ObservableArrayList;

import com.example.stockbrain.model.businessobject.SecurityItem;

public interface ListItemInteractionInterface {
    ObservableArrayList<SecurityItem> getCompanyList();
    AllCompanyDetails getAllCompanyDetails(String ticker);
    void createCompany(String ticker);
    void deleteCompany(String ticker);
}