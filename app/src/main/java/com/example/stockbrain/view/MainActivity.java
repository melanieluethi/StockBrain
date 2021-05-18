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
import com.example.stockbrain.viewmodel.AllCompanyDetails;
import com.example.stockbrain.viewmodel.CompanyAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    CompanyAdapter companyAdapter = new CompanyAdapter(this);
    ListView listView;
    String stTicker = "";
    String stAddNewCompanyTicker = "";
    View mainView;
    String companyPicked = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        companyAdapter.createCompany("AAPL");
        companyAdapter.createCompany("GOOG"  );
        companyAdapter.createCompany("MSFT");
        companyAdapter.createCompany("TSLA");
        companyAdapter.createCompany("VOW.DE");
        companyAdapter.createCompany("SBUX");
        buildList();

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
                        companyAdapter.createCompany(stAddNewCompanyTicker);
                        buildList();
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
        companyAdapter.getCompanyList().forEach(si -> htCompanies.put(si.getTickerSymbol(), si.getName()));
        String[] saCompanies = new String[htCompanies.size()];
        saCompanies = htCompanies.values().toArray(saCompanies);

        ListAdapter theAdapter = new ListViewAdapter(MainActivity.this, saCompanies);

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

                companyAdapter.getCompanyDetails(stTicker);
                mainView = view;
                companyPicked = stCompanyPicked;
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                String stCompanyPicked = String.valueOf(adapterView.getItemAtPosition(position));
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
                companyAdapter.deleteCompany(stTicker);
                Toast.makeText(getApplicationContext(), "Deleted company " + stCompanyPicked, Toast.LENGTH_LONG).show();
                buildList();
            } });

        adDeleteCompany.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_LONG).show();
            } });

        AlertDialog alertDialog = adDeleteCompany.create();
        alertDialog.show();
    }

    public View getMainView() {
        return mainView;
    }

    public String getCompanyPicked() {
        return companyPicked;
    }

    public void messageSuccessfully(String successfully) {
        Toast.makeText(MainActivity.this, "Successfully! " + successfully + ".", Toast.LENGTH_SHORT).show();
    }

    public void messageWentWrong(String error) {
        Toast.makeText(MainActivity.this, "Something went wrong! Couldn't " + error + ".", Toast.LENGTH_SHORT).show();
    }
}