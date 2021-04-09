package com.example.stockbrain.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.stockbrain.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] Companies = {"Microsoft", "Apple", "Google"};

        ListAdapter theAdapter = new listViewAdapter(this, Companies);

        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(theAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String tvShowPicked = "You Selected " + String.valueOf(adapterView.getItemAtPosition(position));

                Toast.makeText(MainActivity.this, tvShowPicked, Toast.LENGTH_LONG).show();

            }
        });
    }

}