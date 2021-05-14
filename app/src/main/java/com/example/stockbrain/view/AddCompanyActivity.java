package com.example.stockbrain.view;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockbrain.R;
import com.google.android.material.textfield.TextInputLayout;

public class AddCompanyActivity extends AppCompatActivity {

    private TextView tvAddNewCompanyNameTile, tvAddNewCompanyTickerTile, tvAddNewCompanyExchangeTile;
    private Spinner spAddNewCompanyExchange;
    private TextInputLayout tiAddNewCompanyName, tiAddNewCompanyTicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcompany);

        tvAddNewCompanyNameTile = findViewById(R.id.tvAddNewCompanyNameTile);
        tvAddNewCompanyTickerTile = findViewById(R.id.tvAddNewCompanyTickerTile);
        tvAddNewCompanyExchangeTile = findViewById(R.id.tvAddNewCompanyExchangeTile);
        spAddNewCompanyExchange = findViewById(R.id.spAddNewCompanyExchange);
        tiAddNewCompanyName = findViewById(R.id.tiAddNewCompanyName);
        tiAddNewCompanyTicker = findViewById(R.id.tiAddNewCompanyTicker);

    }
}
