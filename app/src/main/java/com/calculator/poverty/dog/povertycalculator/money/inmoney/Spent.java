package com.calculator.poverty.dog.povertycalculator.money.inmoney;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import com.calculator.poverty.dog.povertycalculator.R;

import data.Calculation;

/**
 * Created by DoG on 14.10.2017.
 */

public class Spent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spent);

    }


    public void setSpentMoney (View view) {
        TextView getMoney = (TextView) findViewById(R.id.setSpentMoneyText);
        String money = getMoney.getText().toString();
    }

}
