package com.calculator.poverty.dog.povertycalculator.money.settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.calculator.poverty.dog.povertycalculator.money.Money;

/**
 * Created by DoG on 15.10.2017.
 */

public class Settings extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent intent = new Intent(this, Money.class);
        startActivity(intent);
    }
}
