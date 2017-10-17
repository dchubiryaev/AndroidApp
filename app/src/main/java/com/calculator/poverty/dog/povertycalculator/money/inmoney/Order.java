package com.calculator.poverty.dog.povertycalculator.money.inmoney;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.calculator.poverty.dog.povertycalculator.R;

import data.Calculation;

/**
 * Created by DoG on 17.10.2017.
 */

public class Order extends AppCompatActivity {

    private Calculation calculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        calculation = new Calculation(this);

    }
}
