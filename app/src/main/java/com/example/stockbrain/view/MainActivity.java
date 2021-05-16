package com.example.stockbrain.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static java.lang.Thread.*;

public class MainActivity extends AppCompatActivity {
    CompanyListAdapter companyListAdapter = new CompanyListAdapter();
    ListView listView;
    String stTicker = "";
    String stAddNewCompanyTicker = "";
    ObservableArrayList<SecurityItem> oaCompanyList = companyListAdapter.getCompanyList();

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

        oaCompanyList.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<SecurityItem>>() {
            @Override
            public void onChanged(ObservableList<SecurityItem> sender) {
                System.out.println("onChanged ausgeführt");
                buildList();
            }

            @Override
            public void onItemRangeChanged(ObservableList<SecurityItem> sender, int positionStart, int itemCount) {
                System.out.println("onItemRangeChanged ausgeführt");
                buildList();
            }

            @Override
            public void onItemRangeInserted(ObservableList<SecurityItem> sender, int positionStart, int itemCount) {
                //do nothing
            }

            @Override
            public void onItemRangeMoved(ObservableList<SecurityItem> sender, int fromPosition, int toPosition, int itemCount) {
                System.out.println("onItemRangeMoved ausgeführt");
                buildList();
            }

            @Override
            public void onItemRangeRemoved(ObservableList<SecurityItem> sender, int positionStart, int itemCount) {
                System.out.println("onItemRangeRemoved ausgeführt");
                buildList();
            }
        });

        FloatingActionButton fbAddNewCompany = findViewById(R.id.fbAddNewCompany);
        fbAddNewCompany.setOnClickListener(e -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add New Company");

            View viewInflated = LayoutInflater.from(this).inflate(R.layout.activity_addnewcompany, (ViewGroup) listView, false);

            final TextInputLayout input = (TextInputLayout) viewInflated.findViewById(R.id.tiAddNewCompany);

            builder.setView(viewInflated);

            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    stAddNewCompanyTicker = input.getEditText().getText().toString();
                    if (!(stAddNewCompanyTicker.equals(""))){
                        companyListAdapter.createCompany(stAddNewCompanyTicker);
                        buildList();
                        Toast.makeText(MainActivity.this, "Added Company " + stAddNewCompanyTicker, Toast.LENGTH_LONG);
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

    public void buildList () {
        Hashtable <String, String> htCompanies = new Hashtable<>();
        System.out.println("buildList");
        for (SecurityItem si : oaCompanyList){
            htCompanies.put(si.getTickerSymbol(), si.getName());
        }
        System.out.println("buildList2");
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

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                System.out.println("Long clicked");
                String stCompanyPicked = String.valueOf(adapterView.getItemAtPosition(position));
                System.out.println("Selected: " + stCompanyPicked);
                htCompanies.entrySet().forEach(v -> {
                    if(v.getValue().equals(stCompanyPicked)){
                        stTicker = v.getKey();
                        return;
                    }
                });

                deleteAlert(view, stCompanyPicked, stTicker);
                return true;
            }
        });

    }

    public void sendData(View view, String stCompanyPicked, String stTicker) {
        // Define Intent
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        // Pass Data
        intent.putExtra("COMPANY_NAME", stCompanyPicked);
        intent.putExtra("TICKER", stTicker);

        // Check for empty name
        if (TextUtils.isEmpty(stCompanyPicked)) {
            Toast.makeText(MainActivity.this, "Please select a Company", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(intent);
        }
    }

    public void deleteAlert (View view, String stCompanyPicked, String stTicker) {
        AlertDialog.Builder adDeleteCompany = new AlertDialog.Builder(this);
        adDeleteCompany.setTitle("Do you wish to delete " + stCompanyPicked + "?");
        adDeleteCompany.setIcon(android.R.drawable.ic_dialog_alert);
        adDeleteCompany.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                companyListAdapter.deleteCompany(stTicker);
                Toast.makeText(getApplicationContext(), "Deleted company " + stCompanyPicked, Toast.LENGTH_LONG).show();
                buildList();
            } });

        adDeleteCompany.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_LONG).show();
                //finish();
            } });

        AlertDialog alertDialog = adDeleteCompany.create();
        alertDialog.show();
    }

}