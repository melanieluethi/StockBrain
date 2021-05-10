package com.example.stockbrain.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.stockbrain.R;

import java.util.ArrayList;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Hashtable <String, String> htCompanies = new Hashtable<String, String>();
        htCompanies.put("USA", "Microsoft,Apple,Google");
        htCompanies.put("Schweiz", "Nestle,Novartis,Roche");
        htCompanies.put("UK", "HSBC");
        htCompanies.put("Deutschland", "Rheinmetall,Deutsche Bank");

        Spinner spCountry = (Spinner)findViewById(R.id.spCountry);


        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> Companies = new ArrayList<String>();
                String text = spCountry.getSelectedItem().toString();
                for (String company : htCompanies.get(text).split(",")) {
                    Companies.add(company);
                }

                String[] stCompanies = new String[Companies.size()];
                stCompanies = Companies.toArray(stCompanies);

                ListAdapter theAdapter = new listViewAdapter(MainActivity.this, stCompanies);

                ListView listView = (ListView) findViewById(R.id.lvCompanies);

                listView.setAdapter(theAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                        String stCompanyPicked = "You Selected " + String.valueOf(adapterView.getItemAtPosition(position));

                        Toast.makeText(MainActivity.this, stCompanyPicked, Toast.LENGTH_LONG).show();

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });




        /**
         * Referenz auf die Datenbank (Sollte in derjenigen Methode sein, wo der Button "show Data" aufgerufen wird)
         */
        // StockBrainDatabaseHelper stockBrainDatabaseHelper = new StockBrainDatabaseHelper(MainActivity.this);

    }
}

