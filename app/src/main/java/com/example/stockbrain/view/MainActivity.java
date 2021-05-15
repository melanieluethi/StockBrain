package com.example.stockbrain.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.stockbrain.R;
import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.viewmodel.AllCompanyDetails;
import com.example.stockbrain.viewmodel.CompanyListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    CompanyListAdapter companyListAdapter = new CompanyListAdapter();
    ListView listView;
    String stTicker = "";
    String stAddNewCompanyTicker = "";

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

        listView = findViewById(R.id.lvCompanies);
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

        fbAddNewCompany.setOnClickListener(e -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add New Company");
            // I'm using fragment here so I'm using getView() to provide ViewGroup
            // but you can provide here any other instance of ViewGroup from your Fragment / Activity
            View viewInflated = LayoutInflater.from(this).inflate(R.layout.activity_addnewcompany, (ViewGroup) listView, false);
            // Set up the input
            final TextInputLayout input = (TextInputLayout) viewInflated.findViewById(R.id.tiAddNewCompany);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            builder.setView(viewInflated);

            // Set up the buttons
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    stAddNewCompanyTicker = input.getEditText().getText().toString();
                    System.out.println("New Ticker:" + stAddNewCompanyTicker);
                    System.out.println(stAddNewCompanyTicker);
                    if (!(stAddNewCompanyTicker.equals(""))){
                        System.out.println("New Ticker:" + stAddNewCompanyTicker);
                        companyListAdapter.createCompany(stAddNewCompanyTicker);
                        rebuildList();
                    }
                }
            });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        });
    }

    public void rebuildList () {
        Hashtable <String, String> htCompanies = new Hashtable<>();
        for (SecurityItem si : companyListAdapter.getCompanyList()){
            htCompanies.put(si.getTickerSymbol(), si.getName());
        }

        String[] saCompanies = new String[htCompanies.size()];
        saCompanies = htCompanies.values().toArray(saCompanies);

        ListAdapter theAdapter = new listViewAdapter(MainActivity.this, saCompanies);

        listView.setAdapter(theAdapter);

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