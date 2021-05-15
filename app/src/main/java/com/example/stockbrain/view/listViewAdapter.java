package com.example.stockbrain.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.stockbrain.R;
import com.example.stockbrain.model.businessobject.SecurityItem;
import com.example.stockbrain.viewmodel.CompanyListAdapter;
import com.squareup.picasso.Picasso;

public class listViewAdapter extends ArrayAdapter<String>  {

    CompanyListAdapter companyListAdapter = new CompanyListAdapter();

    public listViewAdapter(Context context, String[] values) {
        super(context,
                R.layout.row_layout_2,
                values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View theView = inflater.inflate(R.layout.row_layout_2, parent, false);

        String stCompany = getItem(position);

        TextView textView = (TextView) theView.findViewById(R.id.textView1);

        textView.setText(stCompany);

        ImageView imageView = (ImageView) theView.findViewById(R.id.imageView1);
        String stImageURL = "";

        for (SecurityItem si : companyListAdapter.getCompanyList()){
            if (si.getName().equals(stCompany)) {
                stImageURL = si.getUrlLogo();
            }
        }

        Glide.with(getContext()).load(stImageURL).into(imageView);

        return theView;
    }
}