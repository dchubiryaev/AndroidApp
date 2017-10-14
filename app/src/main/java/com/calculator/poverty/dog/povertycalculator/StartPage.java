package com.calculator.poverty.dog.povertycalculator;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.calculator.poverty.dog.povertycalculator.money.Money;
import com.calculator.poverty.dog.povertycalculator.tasks.Tasks;

import static android.R.attr.button;
import static android.R.attr.left;
import static android.R.attr.right;
import static com.calculator.poverty.dog.povertycalculator.R.id.bottom;
import static com.calculator.poverty.dog.povertycalculator.R.id.top;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

    }

    public void ButtonClickMoney(View view)
    {
        Intent intObj = new Intent(this, Money.class);
        startActivity(intObj);
    }

    public void ButtonClickTasks(View view)
    {
        Intent intObj = new Intent(this, Tasks.class);
        startActivity(intObj);
    }
}
