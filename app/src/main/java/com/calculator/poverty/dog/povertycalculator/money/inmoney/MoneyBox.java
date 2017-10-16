package com.calculator.poverty.dog.povertycalculator.money.inmoney;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.calculator.poverty.dog.povertycalculator.R;
import com.calculator.poverty.dog.povertycalculator.money.ListMoney;

import java.util.ArrayList;

import data.DatabaseSpentHelper;

/**
 * Created by DoG on 15.10.2017.
 */

public class MoneyBox extends AppCompatActivity {
    Context context;
    ArrayList<ListMoney> arr;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneybox);
        setListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                System.out.println("itemClick: position = " + position + ", id = " + id);

            }
        });

        lv.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Intent intent=new Intent ( MoneyBox.this , Spent.class );
                startActivity(intent);
                // Возвращает "истину", чтобы завершить событие клика, чтобы
                // onListItemClick больше не вызывался
                return true;
            }
        });

    }

    public void onItemClick (AdapterView<?> parent, View view, int position, long id){
        System.out.println("bla bla bla");
    }

    public void setListView () {
        final DatabaseSpentHelper thing = new DatabaseSpentHelper(this);
        ArrayList<ListMoney> data = thing.getThingNotSpent();
        arr = new ArrayList<>();
        lv = (ListView) this.findViewById(R.id.listView);
        lv.setAdapter(new MoneyBoxAdapter(this,data));

    }

    public void addMoneyBox(View view){
        Intent intent = new Intent ( MoneyBox.this , AddMoneyBox.class );
        startActivity(intent);
    }


}
