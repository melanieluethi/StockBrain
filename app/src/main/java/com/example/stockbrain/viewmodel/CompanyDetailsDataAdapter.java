package com.example.stockbrain.viewmodel;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.stockbrain.model.businessobject.DailyPrice;
import com.example.stockbrain.model.businessobject.DailyPriceBuilder;
import com.example.stockbrain.model.businessobject.FundamentalData;
import com.example.stockbrain.model.businessobject.FundamentalDataBuilder;
import com.example.stockbrain.model.database.RepositoryProvider;
import com.example.stockbrain.model.rest.pojo.CompanyPojo;
import com.example.stockbrain.model.rest.service.StockBrainService;
import com.example.stockbrain.model.rest.util.RestConstants;
import com.example.stockbrain.model.rest.util.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CompanyDetailsDataAdapter {
    private CompanyAdapter companyAdapter;

    public CompanyDetailsDataAdapter(CompanyAdapter companyAdapter){
        this.companyAdapter = companyAdapter;
    }

    protected void getCompanyDetails(String ticker) {
        getCompanyPrices(ticker);
    }

    private void getCompanyPrices(String ticker) {
        StockBrainService service = RetrofitClientInstance.getStockRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyPojo>> call = service.getCompanyPrices(ticker);
        call.enqueue(new Callback<List<CompanyPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyPojo>> call, Response<List<CompanyPojo>> response) {
                if(response.isSuccessful()) {
                    List<Object> data = response.body().get(0).getData();
                    String[] dataPrices = data.get(data.size() - 1).toString().split(",");
                    Double closingPrice = 0.0;
                    Double volume = 0.0;
                    if(!dataPrices[7].contains("null"))
                        closingPrice = Double.parseDouble(dataPrices[7]);
                    if(!dataPrices[8].contains("null"))
                        volume = Double.parseDouble(dataPrices[8]);
                    DailyPrice dailyPrice = new DailyPriceBuilder()
                            .withTickerSymbol(ticker)
                            .withClosingPrice(closingPrice)
                            .withVolume(volume.intValue())
                            .build();
                    RepositoryProvider.getDailyPriceRepositoryInstance().saveEntity(dailyPrice);
                    Log.d("getCompanyPrices", "Successfully");
                    getCompanyFundamentalData(ticker);
                } else {
                    companyAdapter.messageWentWrong("save Prices");
                    Log.d("getCompanyPrices", "Response Failed");
                }
            }

            @Override
            public void onFailure(Call<List<CompanyPojo>> call, Throwable t) {
                companyAdapter.messageWentWrong("save Prices");
                Log.d("getCompanyPrices", "FAILED");
            }
        });
    }

    private void getCompanyFundamentalData(String ticker) {
        StockBrainService service = RetrofitClientInstance.getStockRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyPojo>> call = service.getCompanyStatements(ticker, RestConstants.STATEMENT_BALANCE_SHEET, RestConstants.PERIOD, RestConstants.FYEAR);
        call.enqueue(new Callback<List<CompanyPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyPojo>> call, Response<List<CompanyPojo>> response) {
                if(response.isSuccessful()) {
                    String[] dataStatements = response.body().get(0).getData().get(0).toString().split(",");
                    Double revenue = 0.0;
                    Double assets = 0.0;
                    Double liabilities = 0.0;
                    if (!dataStatements[76].contains("null"))
                        revenue = Double.parseDouble(dataStatements[76]);
                    if (!dataStatements[30].contains("null"))
                        assets = Double.parseDouble(dataStatements[30]);
                    if (!dataStatements[82].contains("null"))
                        liabilities = Double.parseDouble(dataStatements[82]);
                    FundamentalData fundamentalData = new FundamentalDataBuilder()
                            .withTickerSymbol(ticker)
                            .withRevenue(revenue)
                            .withAssets(assets)
                            .withLiabilities(liabilities)
                            .build();
                    getCompanyFundamentalDataProfit(ticker, fundamentalData);
                    Log.d("getCompanyFundamentalData", "Successfully");
                } else {
                    companyAdapter.messageWentWrong("save Fundamental Data");
                    Log.d("getCompanyFundamentalData", "Response Failed");
                }
            }

            @Override
            public void onFailure(Call<List<CompanyPojo>> call, Throwable t) {
                companyAdapter.messageWentWrong("save Fundamental Data");
                Log.d("getCompanyFundamentalData", "FAILED");
            }
        });
    }

    private void getCompanyFundamentalDataProfit(String ticker, FundamentalData fundamentalData) {
        StockBrainService service = RetrofitClientInstance.getStockRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyPojo>> call = service.getCompanyStatements(ticker, RestConstants.STATEMENT_PROFIT_LOSS, RestConstants.PERIOD, RestConstants.FYEAR);
        call.enqueue(new Callback<List<CompanyPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyPojo>> call, Response<List<CompanyPojo>> response) {
                if(response.isSuccessful()) {
                    String[] dataProfit = response.body().get(0).getData().get(0).toString().split(",");
                    Double profit = 0.0;
                    if (!dataProfit[18].equals("null"))
                        profit = Double.parseDouble(dataProfit[18]);
                    fundamentalData.setProfit(profit);
                    RepositoryProvider.getFundamentalDataRepositoryInstance().saveEntity(fundamentalData);
                    companyAdapter.messageSuccessfully("Saved Detail Data.");
                    companyAdapter.sendData(ticker);
                    Log.d("getCompanyFundamentalDataProfit", "Successfully");
                } else {
                    companyAdapter.messageWentWrong("save Fundamental Data");
                    Log.d("getCompanyFundamentalDataProfit", "Response Failed");
                }
            }

            @Override
            public void onFailure(Call<List<CompanyPojo>> call, Throwable t) {
                companyAdapter.messageWentWrong("save Fundamental Data");
                Log.d("getCompanyFundamentalDataProfit", "FAILED");
            }
        });
    }
}