package com.example.stockbrain.viewmodel;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.database.RepositoryProvider;
import com.example.stockbrain.model.database.SecurityItemRepository;
import com.example.stockbrain.model.rest.pojo.CompanyPojo;
import com.example.stockbrain.model.rest.pojo.CompanyStatementsPojo;
import com.example.stockbrain.model.rest.service.StockBrainService;
import com.example.stockbrain.model.rest.util.RestConstants;
import com.example.stockbrain.model.rest.util.RetrofitClientInstance;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyDetailsGetAdapter {
    @SuppressLint("NewApi")
    private SecurityItemRepository securityItemRepository = RepositoryProvider.getSecurityItemRepositoryInstance();

    public void getCompanyGeneral(String ticker) {
        StockBrainService service = RetrofitClientInstance.getStockRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyPojo>> call = service.getCompany(ticker);
        call.enqueue(new Callback<List<CompanyPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyPojo>> call, Response<List<CompanyPojo>> response) {
                if(response.isSuccessful()) {
                    // TODO LUM: Convert Response in DAO
                    List<Object> generalData = response.body().get(0).getData();
                    String companyName = generalData.get(2).toString();
                    SecurityItem securityItem = new SecurityItem(generalData.get(1).toString(), companyName, getImage(companyName));
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

    private Bitmap getImage(String copmanyName) {
        // TODO LUM: Implement Rest to get Company Logo
        final Bitmap[] bmp = new Bitmap[1];
        StockBrainService service = RetrofitClientInstance.getLogoRetrofitInstance().create(StockBrainService.class);
        Call<ResponseBody> call = service.getCompanyLogo(copmanyName + ".com");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    InputStream inputStream = response.body().byteStream();
                    bmp[0] = BitmapFactory.decodeStream(inputStream);
                    Log.d("getImage", "Successfully! " + inputStream.toString() + " " + bmp[0]);
                } else {
                    // TODO LUM: Incorrect Response - get old value form database
                    Log.d("getImage", "Response Failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // TODO LUM: What happens if it fails?
                Log.d("getImage", "FAILED");
            }
        });
        return bmp[0];
    }

    public void getCompanyPrices(String ticker) {
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

    public void getCompanyStatements(String ticker) {
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