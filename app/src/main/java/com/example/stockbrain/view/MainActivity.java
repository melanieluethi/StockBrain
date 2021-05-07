package com.example.stockbrain.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.stockbrain.R;
//import com.example.stockbrain.model.database.StockBrainDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] Companies = {"Microsoft", "Apple", "Google"};

        ListAdapter theAdapter = new listViewAdapter(this, Companies);

        ListView listView = (ListView) findViewById(R.id.lvCompanies);

        listView.setAdapter(theAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String stCompanyPicked = "You Selected " + String.valueOf(adapterView.getItemAtPosition(position));

                Toast.makeText(MainActivity.this, stCompanyPicked, Toast.LENGTH_LONG).show();

            }
        });


        /**
         * Referenz auf die Datenbank (Sollte in derjenigen Methode sein, wo der Button "show Data" aufgerufen wird)
         */
        // StockBrainDatabaseHelper stockBrainDatabaseHelper = new StockBrainDatabaseHelper(MainActivity.this);

    }

}