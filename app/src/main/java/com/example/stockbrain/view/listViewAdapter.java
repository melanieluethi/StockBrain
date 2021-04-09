package com.example.stockbrain.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stockbrain.R;

public class listViewAdapter extends ArrayAdapter<String>  {

    public listViewAdapter(Context context, String[] values) {
        super(context,
                R.layout.row_layout_2,
                values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View theView = inflater.inflate(R.layout.row_layout_2, parent, false);

        String tvShows = getItem(position);

        TextView textView = (TextView) theView.findViewById(R.id.textView1);

        textView.setText(tvShows);

        ImageView imageView = (ImageView) theView.findViewById(R.id.imageView1);

        imageView.setImageResource(R.drawable.microsoft_logo);

        return theView;
    }
}