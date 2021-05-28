package com.example.stockbrain.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.bumptech.glide.Glide;
import com.example.stockbrain.R;
import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.viewmodel.AllCompanyDetails;
import com.example.stockbrain.viewmodel.CompanyAdapter;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDetailCompanyName, tvDetailCompanyTicker;
    private TextView tvAssets, tvLiabilities, tvProfit, tvRevenue, tvVolume, tvClosingPrice;
    private ImageView ivDetailCompanyLogo;

    String stCompanyName, stTicker;
    CompanyAdapter companyAdapter = new CompanyAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailCompanyName = findViewById(R.id.tvDetailCompanyName);
        tvDetailCompanyTicker = findViewById(R.id.tvDetailCompanyTicker);
        ivDetailCompanyLogo = findViewById(R.id.ivDetailCompanyLogo);
        tvAssets = findViewById(R.id.tvAssets);
        tvLiabilities = findViewById(R.id.tvLiabilities);
        tvProfit = findViewById(R.id.tvProfit);
        tvRevenue = findViewById(R.id.tvRevenue);
        tvVolume = findViewById(R.id.tvVolume);
        tvClosingPrice = findViewById(R.id.tvClosingPrice);

        if (getIntent().hasExtra("COMPANY_NAME") && getIntent().hasExtra("TICKER")) {
            // Get data
            stCompanyName = getIntent().getStringExtra("COMPANY_NAME");
            stTicker = getIntent().getStringExtra("TICKER");
        }

        String stImageURL = "";
        for (SecurityItem si : companyAdapter.getCompanyList()){
            if (si.getName().equals(stCompanyName)) {
                stImageURL = si.getUrlLogo();
            }
        }

        Glide.with(DetailActivity.this).load(stImageURL).into(ivDetailCompanyLogo);

        tvDetailCompanyName.setText(stCompanyName);
        tvDetailCompanyTicker.setText(stTicker);

        NumberFormat form = new DecimalFormat("0.000E00");

        AllCompanyDetails allCompanyDetails = new AllCompanyDetails(stTicker);
        tvAssets.setText(form.format(allCompanyDetails.getFundamentalData().getAssets()));
        tvLiabilities.setText(form.format(allCompanyDetails.getFundamentalData().getLiabilities()));
        tvProfit.setText(form.format(allCompanyDetails.getFundamentalData().getProfit()));
        if (allCompanyDetails.getFundamentalData().getRevenue() == 0) {
            tvRevenue.setText("-");
        } else {
            tvRevenue.setText(form.format(allCompanyDetails.getFundamentalData().getRevenue()));
        }
        tvVolume.setText(allCompanyDetails.getDailyPrice().getVolume().toString());
        tvClosingPrice.setText((allCompanyDetails.getDailyPrice().getClosingPrice()).toString());
    }
}