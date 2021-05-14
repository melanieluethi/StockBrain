package com.example.stockbrain.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockbrain.R;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDetailCompanyName, tvDetailCompanyTicker, tvDetailCompanyExchange;

    String stCompanyName, stTicker, stExchange;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailCompanyName = findViewById(R.id.tvDetailCompanyName);
        tvDetailCompanyTicker = findViewById(R.id.tvDetailCompanyTicker);

        if (getIntent().hasExtra("COMPANY_NAME") && getIntent().hasExtra("TICKER")) {
            // Get data
            stCompanyName = getIntent().getStringExtra("COMPANY_NAME");
            stTicker = getIntent().getStringExtra("TICKER");
        }

        tvDetailCompanyName.setText(stCompanyName);
        tvDetailCompanyTicker.setText(stTicker);
    }

}
