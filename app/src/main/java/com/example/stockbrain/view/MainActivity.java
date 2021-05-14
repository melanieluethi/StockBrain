package com.example.stockbrain.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.stockbrain.R;
import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.viewmodel.AllCompanyDetails;
import com.example.stockbrain.viewmodel.CompanyListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    CompanyListAdapter companyListAdapter = new CompanyListAdapter();

    String stTicker = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        companyListAdapter.createCompany("AAPL");
        companyListAdapter.createCompany("GOOG");
        companyListAdapter.createCompany("MSFT");
        companyListAdapter.createCompany("TSLA");
        companyListAdapter.createCompany("VOW.DE");
        companyListAdapter.createCompany("SBUX");

        Hashtable <String, String> htCompanies = new Hashtable<>();
        for (SecurityItem si : companyListAdapter.getCompanyList()){
            htCompanies.put(si.getTickerSymbol(), si.getName());
        }

        FloatingActionButton fbAddNewCompany = findViewById(R.id.fbAddNewCompany);

        String[] saCompanies = new String[htCompanies.size()];
        saCompanies = htCompanies.values().toArray(saCompanies);

        ListAdapter theAdapter = new listViewAdapter(MainActivity.this, saCompanies);

        ListView listView = findViewById(R.id.lvCompanies);

        listView.setAdapter(theAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String stCompanyPicked = String.valueOf(adapterView.getItemAtPosition(position));

                htCompanies.entrySet().forEach(v -> {
                    if(v.getValue().equals(stCompanyPicked)){
                        stTicker = v.getKey();
                        return;
                    }
                });

                AllCompanyDetails allDetails = companyListAdapter.getAllCompanyDetails(stTicker);
                //Toast.makeText(MainActivity.this, stCompanyPicked, Toast.LENGTH_LONG).show();

                sendData(view, stCompanyPicked, stTicker);

            }
        });
    }

    public void sendData(View view, String stCompanyPicked, String stTicker) {
        // Define Intent
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                            /* Alternative Bundle notation
                            https://stackoverflow.com/questions/34607727/how-to-add-pass-multiple-values-to-intent-object
                            https://zocada.com/using-intents-extras-pass-data-activities-android-beginners-guide/
                            */

        // Pass Data
        intent.putExtra("COMPANY_NAME", stCompanyPicked);
        intent.putExtra("TICKER", stTicker);

        // Check for empty name
        if (TextUtils.isEmpty(stCompanyPicked)) {
            Toast.makeText(MainActivity.this, "Please select a Company", Toast.LENGTH_SHORT).show();
        } else {
            // Normally, when we launch a new activity, the previous activities will be kept in a queue like a stack of activities.
            // So if you want to kill all the previous activities, use FLAG_ACTIVITY_CLEAR_TASK and FLAG_ACTIVITY_NEW_TASK flag
            // on the Intent to clear all the activity stack.
            // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}