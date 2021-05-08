package com.example.stockbrain.viewmodel;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.businessobject.SecurityItemBuilder;
import com.example.stockbrain.model.database.RepositoryProvider;
import com.example.stockbrain.model.database.SecurityItemRepository;
import com.example.stockbrain.model.rest.pojo.CompanyLogoPojo;
import com.example.stockbrain.model.rest.pojo.CompanyPojo;
import com.example.stockbrain.model.rest.pojo.CompanyStatementsPojo;
import com.example.stockbrain.model.rest.service.StockBrainService;
import com.example.stockbrain.model.rest.util.RestConstants;
import com.example.stockbrain.model.rest.util.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CompanyDetailsGetAdapter {
    private SecurityItemRepository securityItemRepository = RepositoryProvider.getSecurityItemRepositoryInstance();

    protected void getCompanyGeneral(String ticker) {
        StockBrainService service = RetrofitClientInstance.getStockRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyPojo>> call = service.getCompany(ticker);
        call.enqueue(new Callback<List<CompanyPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyPojo>> call, Response<List<CompanyPojo>> response) {
                if(response.isSuccessful()) {
                    List<Object> generalData = response.body().get(0).getData();
                    String companyName = generalData.get(2).toString();
                    SecurityItem securityItem = new SecurityItemBuilder()
                            .withTickerSymbol(generalData.get(1).toString())
                            .withItemName(companyName)
                            .build();
                    securityItemRepository.saveEntity(securityItem);
                    Log.d("getCompanyGeneral", "Successfully!");
                } else {
                    // TODO LUM: Incorrect Response - get old value form database
                    Log.d("getCompanyGeneral", "Response Failed");
                }
            }

            @Override
            public void onFailure(Call<List<CompanyPojo>> call, Throwable t) {
                // TODO LUM: What happens if it fails?
                Log.d("getCompanyGeneral", "FAILED");
            }
        });
    }

    protected void getLogoUrl(String ticker, String companyName) {
        // TODO LUM: Implement Rest to get Company Logo
        StockBrainService service = RetrofitClientInstance.getLogoRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyLogoPojo>> call = service.getCompanyLogo(companyName);
        call.enqueue(new Callback<List<CompanyLogoPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyLogoPojo>> call, Response<List<CompanyLogoPojo>> response) {
                if (response.isSuccessful()) {
                    if (!response.body().isEmpty()) {
                        SecurityItem securityItem = new SecurityItemBuilder()
                                .withTickerSymbol(ticker)
                                .withItemName(companyName)
                                .withUrlLogo(response.body().get(0).getLogo())
                                .build();
                        securityItemRepository.saveEntity(securityItem);
                        Log.d("getImage", "Successfully!");
                    } else {
                        String[] s = companyName.split(" ");
                        getLogoUrl(ticker, s[0]);
                    }
                } else {
                    // TODO LUM: Incorrect Response - get old value form database
                    Log.d("getImage", "Response Failed");
                }
            }

            @Override
            public void onFailure(Call<List<CompanyLogoPojo>> call, Throwable t) {
                // TODO LUM: What happens if it fails?
                Log.d("getImage", "FAILED");
            }
        });
    }

    protected void getCompanyPrices(String ticker) {
        StockBrainService service = RetrofitClientInstance.getStockRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyPojo>> call = service.getCompanyPrices(ticker);
        call.enqueue(new Callback<List<CompanyPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyPojo>> call, Response<List<CompanyPojo>> response) {
                if(response.isSuccessful()) {
                    // TODO LUM: Convert Response in DAO
                    List<Object> data = response.body().get(0).getData();
                    Log.d("getCompanyPrices", "Successfull " + data.get(0).toString() + " " + data.get(1) + " " + data.get(2));
                } else {
                    // TODO LUM: Incorrect Response - get old value form database
                    Log.d("getCompanyPrices", "Response Failed");
                }
            }

            @Override
            public void onFailure(Call<List<CompanyPojo>> call, Throwable t) {
                // TODO LUM: What happens if it fails?
                Log.d("getCompanyPrices", "FAILED");
            }
        });
    }

    protected void getCompanyStatements(String ticker) {
        StockBrainService service = RetrofitClientInstance.getStockRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyStatementsPojo>> call = service.getCompanyStatements(ticker, RestConstants.STATEMENT, RestConstants.PERIOD, RestConstants.FYEAR);
        call.enqueue(new Callback<List<CompanyStatementsPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyStatementsPojo>> call, Response<List<CompanyStatementsPojo>> response) {
                if(response.isSuccessful()) {
                    // TODO LUM: Convert Response in DAO
                    List<Object> data = response.body().get(0).getData();
                    String currency = response.body().get(0).getCurrency();
                    Log.d("getCompanyStatements", "Successfull " + data.get(0).toString() + " " + currency);
                } else {
                    // TODO LUM: Incorrect Response - get old value form database
                    Log.d("getCompanyStatements", "Response Failed");
                }
            }

            @Override
            public void onFailure(Call<List<CompanyStatementsPojo>> call, Throwable t) {
                // TODO LUM: What happens if it fails?
                Log.d("getCompanyStatements", "FAILED");
            }
        });
    }
}