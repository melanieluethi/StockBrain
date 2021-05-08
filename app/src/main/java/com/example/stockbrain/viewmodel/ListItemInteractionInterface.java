package com.example.stockbrain.viewmodel;

import com.example.stockbrain.model.businessobject.SecurityItem;

import java.io.IOException;
import java.util.List;

public interface ListItemInteractionInterface {
    List<SecurityItem> getCompanyList();
    void getCompanyDetails(String ticker);
    boolean createCompany(String ticker) throws IOException, InterruptedException;
    boolean deleteCompany(String ticker);
}