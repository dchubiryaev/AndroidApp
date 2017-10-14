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
import android.widget.Toast;

import com.calculator.poverty.dog.povertycalculator.R;
import com.calculator.poverty.dog.povertycalculator.money.ListMoney;
import com.calculator.poverty.dog.povertycalculator.money.Money;

import java.util.Date;

import data.Calculation;
import data.DatabaseSpinnerHelper;
import data.Lists;

/**
 * Created by DoG on 14.10.2017.
 */

public class Spent extends AppCompatActivity {

    private DatabaseSpinnerHelper getMoneyHelper;
    private SQLiteDatabase sqLiteDatabaseGetMoneyHelper;
    private String choseCategory;
    private String money;
    private String comment;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spent);
        setSpinner();
    }


    public void setSpentMoney (View view) {
        TextView getMoney = (TextView) findViewById(R.id.setSpentMoneyText);
        money = getMoney.getText().toString();

        TextView getComment = (TextView) findViewById(R.id.setSpentMoneyCommentText);
        comment = getComment.getText().toString();
        date = new Date();
        ListMoney listMoney = new ListMoney(choseCategory, comment, date.toString(), money, "true");
        Calculation calculation = new Calculation(this);
        calculation.addSend(listMoney);
        Intent intent = new Intent(Spent.this, Money.class);
        startActivity(intent);
    }

    public void setSpinner(){
        final Lists category = new Lists(this);
        String[] data = category.getCategory();
        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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
