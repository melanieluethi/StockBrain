package com.example.stockbrain.viewmodel;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.stockbrain.model.businessobject.DailyPrice;
import com.example.stockbrain.model.businessobject.DailyPriceBuilder;
import com.example.stockbrain.model.businessobject.FundamentalData;
import com.example.stockbrain.model.businessobject.FundamentalDataBuilder;
import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.model.businessobject.SecurityItemBuilder;
import com.example.stockbrain.model.database.DailyPriceRepository;
import com.example.stockbrain.model.database.FundamentalDataRepository;
import com.example.stockbrain.model.database.RepositoryProvider;
import com.example.stockbrain.model.database.SecurityItemRepository;
import com.example.stockbrain.model.rest.pojo.CompanyLogoPojo;
import com.example.stockbrain.model.rest.pojo.CompanyPojo;
import com.example.stockbrain.model.rest.service.StockBrainService;
import com.example.stockbrain.model.rest.util.RestConstants;
import com.example.stockbrain.model.rest.util.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CompanyDetailsGetAdapter {
    private SecurityItemRepository securityItemRepository;
    private DailyPriceRepository dailyPriceRepository;
    private FundamentalDataRepository fundamentalDataRepository;
    private boolean isGettingCompany;
    private boolean isGettingPrices;
    private boolean isGettingFundamentalData;

    public CompanyDetailsGetAdapter(){
        securityItemRepository = RepositoryProvider.getSecurityItemRepositoryInstance();
        dailyPriceRepository = RepositoryProvider.getDailyPriceRepository();
        fundamentalDataRepository = RepositoryProvider.getFundamentalDataRepository();
        isGettingCompany = false;
        isGettingPrices = false;
        isGettingFundamentalData = false;
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
                        securityItemRepository.saveEntity(securityItem);
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

    protected void getCompanyPrices(String ticker) {
        StockBrainService service = RetrofitClientInstance.getStockRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyPojo>> call = service.getCompanyPrices(ticker);
        call.enqueue(new Callback<List<CompanyPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyPojo>> call, Response<List<CompanyPojo>> response) {
                if(response.isSuccessful()) {
                    String[] dataPrices = response.body().get(0).getData().get(0).toString().split(",");
                    Double closingPrice = Double.parseDouble(dataPrices[7]);
                    Double volume = Double.parseDouble(dataPrices[8]);
                    DailyPrice dailyPrice = new DailyPriceBuilder()
                            .withTickerSymbol(dataPrices[1])
                            .withClosingPrice(closingPrice)
                            .withVolume(volume.intValue())
                            .build();
                    dailyPriceRepository.saveEntity(dailyPrice);
                    isGettingPrices = true;
                    Log.d("getCompanyPrices", "Successfully");
                } else {
                    isGettingPrices = false;
                    Log.d("getCompanyPrices", "Response Failed");
                }
            }

            @Override
            public void onFailure(Call<List<CompanyPojo>> call, Throwable t) {
                isGettingPrices = false;
                Log.d("getCompanyPrices", "FAILED");
            }
        });
    }

    protected void getCompanyFundamentalData(String ticker) {
        StockBrainService service = RetrofitClientInstance.getStockRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyPojo>> call = service.getCompanyStatements(ticker, RestConstants.STATEMENT_BALANCE_SHEET, RestConstants.PERIOD, RestConstants.FYEAR);
        call.enqueue(new Callback<List<CompanyPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyPojo>> call, Response<List<CompanyPojo>> response) {
                if(response.isSuccessful()) {
                    String[] dataStatements = response.body().get(0).getData().get(0).toString().split(",");
                    String tickerSymbol = dataStatements[1];
                    Double revenue = Double.parseDouble(dataStatements[76]);
                    Double assets = Double.parseDouble(dataStatements[30]);
                    Double liabilities = Double.parseDouble(dataStatements[82]);
                    FundamentalData fundamentalData = new FundamentalDataBuilder()
                            .withTickerSymbol(tickerSymbol)
                            .withRevenue(revenue)
                            .withAssets(assets)
                            .withLiabilities(liabilities)
                            .build();
                    getCompanyFundamentalDataProfit(ticker, fundamentalData);
                    isGettingFundamentalData = true;
                    Log.d("getCompanyFundamentalData", "Successfully");
                } else {
                    isGettingFundamentalData = false;
                    Log.d("getCompanyFundamentalData", "Response Failed");
                }
            }

            @Override
            public void onFailure(Call<List<CompanyPojo>> call, Throwable t) {
                isGettingFundamentalData = false;
                Log.d("getCompanyFundamentalData", "FAILED");
            }
        });
    }

    protected void getCompanyFundamentalDataProfit(String ticker, FundamentalData fundamentalData) {
        StockBrainService service = RetrofitClientInstance.getStockRetrofitInstance().create(StockBrainService.class);
        Call<List<CompanyPojo>> call = service.getCompanyStatements(ticker, RestConstants.STATEMENT_PROFIT_LOSS, RestConstants.PERIOD, RestConstants.FYEAR);
        call.enqueue(new Callback<List<CompanyPojo>>() {
            @Override
            public void onResponse(Call<List<CompanyPojo>> call, Response<List<CompanyPojo>> response) {
                if(response.isSuccessful()) {
                    String[] dataProfit = response.body().get(0).getData().get(0).toString().split(",");
                    Double profit = Double.parseDouble(dataProfit[18]);
                    fundamentalData.setProfit(profit);
                    fundamentalDataRepository.saveEntity(fundamentalData);
                    isGettingFundamentalData = true;
                    Log.d("getCompanyFundamentalDataProfit", "Successfully");
                } else {
                    isGettingFundamentalData = false;
                    Log.d("getCompanyFundamentalDataProfit", "Response Failed");
                }
            }

            @Override
            public void onFailure(Call<List<CompanyPojo>> call, Throwable t) {
                isGettingFundamentalData = false;
                Log.d("getCompanyFundamentalDataProfit", "FAILED");
            }
        });
    }

    public boolean isGettingCompany() {
        return isGettingCompany;
    }

    public void setGettingCompany(boolean gettingCompany) {
        isGettingCompany = gettingCompany;
    }

    public boolean isGettingPrices() {
        return isGettingPrices;
    }

    public void setGettingPrices(boolean gettingPrices) {
        isGettingPrices = gettingPrices;
    }

    public boolean isGettingFundamentalData() {
        return isGettingFundamentalData;
    }

    public void setGettingFundamentalData(boolean gettingFundamentalData) {
        isGettingFundamentalData = gettingFundamentalData;
    }
}