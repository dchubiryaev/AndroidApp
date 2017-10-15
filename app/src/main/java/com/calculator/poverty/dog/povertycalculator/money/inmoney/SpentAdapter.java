package com.calculator.poverty.dog.povertycalculator.money.inmoney;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.calculator.poverty.dog.povertycalculator.R;

import java.util.ArrayList;

/**
 * Created by DoG on 15.10.2017.
 */

public class SpentAdapter extends ArrayAdapter<String> {
    private Activity context;
    ArrayList<String> data = null;

    public SpentAdapter(Activity context, int resource, ArrayList<String> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
    }


    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) { // этот код выполняется, когда вы нажимаете на спиннер
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.spinnerdropdown, parent, false);
        }

        String item = data.get(position);

        if (item != null) { // парсим данные с каждого объекта
            TextView myCountry = (TextView) row.findViewById(R.id.categoryname);
            if (myCountry != null) {
//                myCountry.setText(item.getCountryName());
            }


        } return row;

    }
}
