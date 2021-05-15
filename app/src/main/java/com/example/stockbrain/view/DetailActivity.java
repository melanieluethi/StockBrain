package com.example.stockbrain.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.stockbrain.R;
import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.viewmodel.CompanyListAdapter;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDetailCompanyName, tvDetailCompanyTicker;
    private ImageView ivDetailCompanyLogo;

    String stCompanyName, stTicker;
    CompanyListAdapter companyListAdapter = new CompanyListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailCompanyName = findViewById(R.id.tvDetailCompanyName);
        tvDetailCompanyTicker = findViewById(R.id.tvDetailCompanyTicker);
        ivDetailCompanyLogo = findViewById(R.id.ivDetailCompanyLogo);

        if (getIntent().hasExtra("COMPANY_NAME") && getIntent().hasExtra("TICKER")) {
            // Get data
            stCompanyName = getIntent().getStringExtra("COMPANY_NAME");
            stTicker = getIntent().getStringExtra("TICKER");
        }

        String stImageURL = "";
        for (SecurityItem si : companyListAdapter.getCompanyList()){
            if (si.getName().equals(stCompanyName)) {
                stImageURL = si.getUrlLogo();
            }
        }

        Glide.with(DetailActivity.this).load(stImageURL).into(ivDetailCompanyLogo);

        tvDetailCompanyName.setText(stCompanyName);
        tvDetailCompanyTicker.setText(stTicker);
    }

}
