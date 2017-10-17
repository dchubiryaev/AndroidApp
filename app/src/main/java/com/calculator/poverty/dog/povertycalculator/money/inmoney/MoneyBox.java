package com.calculator.poverty.dog.povertycalculator.money.inmoney;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.calculator.poverty.dog.povertycalculator.R;
import com.calculator.poverty.dog.povertycalculator.money.ListMoney;

import java.util.ArrayList;

import data.DatabaseSpentHelper;

/**
 * Created by DoG on 15.10.2017.
 */

public class MoneyBox extends AppCompatActivity {

    ArrayList<ListMoney> data;
    String money;
    ListView lv;
    ListMoney listMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneybox);
        setListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                listMoney = data.get(position);
                System.out.println(listMoney.getThing());
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

    public void setListView () {
        final DatabaseSpentHelper thing = new DatabaseSpentHelper(this);
        data = thing.getThingNotSpent();
        lv = (ListView) this.findViewById(R.id.listView);
        lv.setAdapter(new MoneyBoxAdapter(this,data));

    }

    public void addPositionToMoneyBox(View view){
        Intent intent = new Intent ( MoneyBox.this , AddPositionToMoneyBox.class );
        startActivity(intent);
    }

    public void addMoney(View view){
        final DatabaseSpentHelper thing = new DatabaseSpentHelper(this);
        TextView getMoney = (TextView) findViewById(R.id.setMoneyText);

        int beforeMoney = Integer.parseInt(listMoney.getMoney());
        int addMoney = Integer.parseInt(getMoney.getText().toString());
        int sum = beforeMoney+addMoney;
        listMoney.setMoney(""+sum);
        thing.addMoneyToBox(listMoney);
        getMoney.setText("");
        recreate();
    }



}
