package com.calculator.poverty.dog.povertycalculator.money.inmoney;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.calculator.poverty.dog.povertycalculator.R;
import com.calculator.poverty.dog.povertycalculator.money.ListMoney;

import java.util.ArrayList;

import data.Calculation;
import data.DatabaseSpentHelper;
import data.Lists;

/**
 * Created by DoG on 15.10.2017.
 */

public class MoneyBox extends AppCompatActivity {
    Context context;
    ArrayList<ListMoney> arr;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DatabaseSpentHelper thing = new DatabaseSpentHelper(this);
        ArrayList<ListMoney> data = thing.getThingNotSpent();



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneybox);

        arr = new ArrayList<>();

        lv = (ListView) this.findViewById(R.id.listView);
        lv.setAdapter(new MoneyBoxAdapter(this,data));



    }

    public void onItemClick (AdapterView<?> parent, View view, int position, long id){
        System.out.println("bla bla bla");
    }

    public void setListView (Context context, ArrayList<ListMoney> arr) {

    }
}
