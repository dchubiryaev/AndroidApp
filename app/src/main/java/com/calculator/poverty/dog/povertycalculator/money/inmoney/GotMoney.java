package com.calculator.poverty.dog.povertycalculator.money.inmoney;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.calculator.poverty.dog.povertycalculator.R;
import com.calculator.poverty.dog.povertycalculator.money.ListGotMoney;
import com.calculator.poverty.dog.povertycalculator.money.Money;

import java.util.Date;

import data.Calculation;
import data.DatabaseSpinnerHelper;
import data.Lists;

/**
 * Created by DoG on 14.10.2017.
 */

public class GotMoney extends AppCompatActivity {

    private String choseCategory;
    private String money;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gotmoney);
        setSpinner();
    }


    public void setGotMoney(View view) {
        TextView getMoney = (TextView) findViewById(R.id.setGotMoneyText);
        money = getMoney.getText().toString();
        date = new Date();
        ListGotMoney listGotMoney = new ListGotMoney(choseCategory, date.toString(), money);
        Calculation calculation = new Calculation(this);
        calculation.addGot(listGotMoney);
        Intent intent = new Intent(GotMoney.this, Money.class);
        startActivity(intent);
    }

    public void setSpinner(){
        final Lists category = new Lists(this);
        String[] data = category.getCategoryGot();
        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinnerstart, R.id.categoryname, data);
        adapter.setDropDownViewResource(R.layout.spinnerdropdown);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        // выделяем элемент
        spinner.setSelection(0);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                choseCategory = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}
