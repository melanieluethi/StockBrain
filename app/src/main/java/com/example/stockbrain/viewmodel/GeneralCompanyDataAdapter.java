package com.example.stockbrain.viewmodel;

import android.util.Log;

import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.businessobject.SecurityItemBuilder;
import com.example.stockbrain.model.database.RepositoryProvider;
import com.example.stockbrain.model.database.StockBrainRepository;
import com.example.stockbrain.model.rest.pojo.CompanyLogoPojo;
import com.example.stockbrain.model.rest.pojo.CompanyPojo;
import com.example.stockbrain.model.rest.service.StockBrainService;
import com.example.stockbrain.model.rest.util.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralCompanyDataAdapter {
    private CompanyAdapter companyAdapter;
    private StockBrainRepository stockBrainRepository;
    private boolean isGettingCompany;

    public GeneralCompanyDataAdapter(CompanyAdapter companyAdapter){
        this.companyAdapter = companyAdapter;
        stockBrainRepository = RepositoryProvider.getStockBrainRepositoryInstance();
        isGettingCompany = false;
    }

    protected void getCompanyGeneral(String ticker) {
        StockBrainService service = RetrofitClientInstance.getStockRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyPojo>> call = service.getCompany(ticker);
        call.enqueue(new Callback<List<CompanyPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyPojo>> call, Response<List<CompanyPojo>> response) {
                if(response.isSuccessful() && response.body().get(0).isFound()) {
                    List<Object> generalData = response.body().get(0).getData();
                    String companyName = generalData.get(2).toString();
                    SecurityItem securityItem = new SecurityItemBuilder()
                            .withTickerSymbol(generalData.get(1).toString())
                            .withItemName(companyName)
                            .build();
                    if (companyName.equals("APPLE INC")) {
                        companyName = "Apple";
                    }
                    getLogoUrl(companyName, securityItem);
                    isGettingCompany = true;
                    Log.d("getCompanyGeneral", "Successfully!");
                } else {
                    isGettingCompany = false;
                    Log.d("getCompanyGeneral", "Response Failed");
                }
            }

            @Override
            public void onFailure(Call<List<CompanyPojo>> call, Throwable t) {
                isGettingCompany = false;
                Log.d("getCompanyGeneral", "FAILED");
            }
        });
    }

    protected void getLogoUrl(String companyName, SecurityItem securityItem) {
        StockBrainService service = RetrofitClientInstance.getLogoRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyLogoPojo>> call = service.getCompanyLogo(companyName);
        call.enqueue(new Callback<List<CompanyLogoPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyLogoPojo>> call, Response<List<CompanyLogoPojo>> response) {
                if (response.isSuccessful()) {
                    if (!response.body().isEmpty()) {
                        String url = response.body().get(0).getLogo();
                        securityItem.setUrlLogo(url);
                        stockBrainRepository.saveEntity(securityItem);
                        companyAdapter.addCompanyList(securityItem);
                        companyAdapter.buildMainActivityList();
                        isGettingCompany = true;
                        Log.d("getImage", "Successfully!");
                    } else {
                        String[] s = companyName.split(" ");
                        getLogoUrl(s[0], securityItem);
                    }
                } else {
                    isGettingCompany = false;
                    Log.d("getImage", "Response Failed");
                }
            }

            @Override
            public void onFailure(Call<List<CompanyLogoPojo>> call, Throwable t) {
                isGettingCompany = false;
                Log.d("getImage", "FAILED");
            }
        });
    }

    public boolean isGettingCompany() {
        return isGettingCompany;
    }

    public void setGettingCompany(boolean gettingCompany) {
        isGettingCompany = gettingCompany;
    }
}