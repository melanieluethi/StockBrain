package com.example.stockbrain.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockbrain.R;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDetailCompanyName, tvDetailCompanyTicker, tvDetailCompanyExchange;

    String stCompanyName, stCountry, stExchange;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailCompanyName = findViewById(R.id.tvDetailCompanyName);
        tvDetailCompanyTicker = findViewById(R.id.tvDetailCompanyTicker);
        tvDetailCompanyExchange = findViewById(R.id.tvDetailCompanyExchange);

        if (getIntent().hasExtra("COMPANY_NAME") && getIntent().hasExtra("COUNTRY")) {
            // Get data
            stCompanyName = getIntent().getStringExtra("COMPANY_NAME");
            stCountry = getIntent().getStringExtra("COUNTRY");

            switch (stCountry) {
                case "Schweiz": {
                    stExchange = "SWIX";
                    break;
                }
                case "Deutschland": {
                    stExchange = "XFRA";
                    break;
                }
                case "UK": {
                    stExchange = "LSE";
                    break;
                }
                case "USA": {
                    stExchange = "NASDAQ";
                    break;
                }
            }
        }

        tvDetailCompanyName.setText(stCompanyName);
        tvDetailCompanyExchange.setText(stExchange);
    }

}
