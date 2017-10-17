package com.calculator.poverty.dog.povertycalculator.money;

;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import com.calculator.poverty.dog.povertycalculator.R;
import com.calculator.poverty.dog.povertycalculator.StartPage;
import com.calculator.poverty.dog.povertycalculator.money.inmoney.GotMoney;
import com.calculator.poverty.dog.povertycalculator.money.inmoney.MoneyBox;
import com.calculator.poverty.dog.povertycalculator.money.inmoney.Order;
import com.calculator.poverty.dog.povertycalculator.money.inmoney.Settings;
import com.calculator.poverty.dog.povertycalculator.money.inmoney.Spent;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Date;

import data.Calculation;


public class Money extends AppCompatActivity {

    private Calculation calculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        calculation = new Calculation(this);
        setText();
//        setGraph();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent intent = new Intent(this, StartPage.class);
        startActivity(intent);
    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                this);
        quitDialog.setTitle("Выход: Вы уверены?");

        quitDialog.setPositiveButton("Таки да!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                finish();
            }
        });

        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });

        quitDialog.show();
    }

    public void ButtonSpent(View view){
        Intent intent = new Intent(Money.this, Spent.class);
        startActivity(intent);
    }

    public void ButtonGot(View view)
    {
        Intent intObj = new Intent(this, GotMoney.class);
        startActivity(intObj);
    }

    public void ButtonSettings (View view)
    {
        Intent intObj = new Intent(this, Settings.class);
        startActivity(intObj);
    }

    public void ButtonMoneyBox(View view)
    {
        Intent intObj = new Intent(this, MoneyBox.class);
        startActivity(intObj);
    }

    public void ButtonOrder (View view)
    {
//        Intent intObj = new Intent(this, Order.class);
//        startActivity(intObj);
    }

//    private void setGraph() {
//        GraphView graph = (GraphView) findViewById(R.id.graph);
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
//                new DataPoint(0, 1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3)
//        });
//        graph.addSeries(series);
//    }

    private void setText(){
        TextView textBal = (TextView)findViewById(R.id.textBal); // Инициализируем компонент
        textBal.setText("Your Balance:");// задаём текст
        textBal.setTextColor(Color.rgb(0,0,0));//black color
        textBal.setTextSize(TypedValue.COMPLEX_UNIT_MM, 5);
        TextView textBalance = (TextView)findViewById(R.id.textBalance); // Инициализируем компонент
        textBalance.setText("" + calculation.getBalance());// задаём текст
        textBalance.setTextColor(Color.rgb(0,0,0));//black color
        textBalance.setTextSize(TypedValue.COMPLEX_UNIT_MM, 5);

        TextView textRealBal = (TextView)findViewById(R.id.textRealBal); // Инициализируем компонент
        textRealBal.setText("Money for spent:");// задаём текст
        textRealBal.setTextColor(Color.rgb(0,0,0));//black color
        textRealBal.setTextSize(TypedValue.COMPLEX_UNIT_MM, 5);
        TextView textRealBalance = (TextView)findViewById(R.id.textRealBalance); // Инициализируем компонент
        textRealBalance.setText(""+calculation.getBalanceForSpenting());// задаём текст
        textRealBalance.setTextColor(Color.rgb(0,0,0));//black color
        textRealBalance.setTextSize(TypedValue.COMPLEX_UNIT_MM, 5);
    }
}
