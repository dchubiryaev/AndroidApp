package com.calculator.poverty.dog.povertycalculator.money.inmoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.calculator.poverty.dog.povertycalculator.R;
import com.calculator.poverty.dog.povertycalculator.money.ListMoney;

import data.Calculation;
import data.Lists;

/**
 * Created by DoG on 16.10.2017.
 */

public class AddPositionToMoneyBox extends AppCompatActivity {
    private String choseCategory;
    private final String money = "0";
    private String comment;
    private final String date = "0";

//    addNewMoneyBox
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpositiontomoneybox);
        setSpinner();
    }
    //Button addlist
    public void addNewMoneyBox (View view) {
        TextView getComment = (TextView) findViewById(R.id.setSpentMoneyCommentText);
        comment = getComment.getText().toString();
        ListMoney listMoney = new ListMoney(choseCategory, comment, date, money, "false");
        Calculation calculation = new Calculation(this);
        calculation.addSpent(listMoney);
        Intent intent = new Intent(AddPositionToMoneyBox.this, MoneyBox.class);
        startActivity(intent);
    }

    public void setSpinner(){
        final Lists category = new Lists(this);
        String[] data = category.getCategorySpent();

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
