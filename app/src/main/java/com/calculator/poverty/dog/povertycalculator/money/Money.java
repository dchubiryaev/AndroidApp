package com.calculator.poverty.dog.povertycalculator.money;

;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import com.calculator.poverty.dog.povertycalculator.R;
import com.calculator.poverty.dog.povertycalculator.StartPage;
import com.calculator.poverty.dog.povertycalculator.money.gotmoney.GotMoney;
import com.calculator.poverty.dog.povertycalculator.money.moneybox.MoneyBox;
import com.calculator.poverty.dog.povertycalculator.money.settings.Settings;
import com.calculator.poverty.dog.povertycalculator.money.spent.Spent;

import data.UseDatabase;


public class Money extends AppCompatActivity {

    private UseDatabase useDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        useDatabase = new UseDatabase(this);
        setText();
//        setGraph();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent intent = new Intent(this, StartPage.class);
        startActivity(intent);
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
        int balance = useDatabase.getBalance();
        int segmentBalance = balance - useDatabase.getSumMoneyInMoneyBox();
        TextView textBal = (TextView)findViewById(R.id.textBal); // Инициализируем компонент
        textBal.setText("Your Balance:");// задаём текст
        textBal.setTextColor(Color.rgb(0,0,0));//black color
        textBal.setTextSize(TypedValue.COMPLEX_UNIT_MM, 5);
        TextView textBalance = (TextView)findViewById(R.id.textBalance); // Инициализируем компонент
        textBalance.setText("" + balance);// задаём текст
        textBalance.setTextColor(Color.rgb(0,0,0));//black color
        textBalance.setTextSize(TypedValue.COMPLEX_UNIT_MM, 5);

        TextView textRealBal = (TextView)findViewById(R.id.textRealBal); // Инициализируем компонент
        textRealBal.setText("Money for flag:");// задаём текст
        textRealBal.setTextColor(Color.rgb(0,0,0));//black color
        textRealBal.setTextSize(TypedValue.COMPLEX_UNIT_MM, 5);
        TextView textRealBalance = (TextView)findViewById(R.id.textRealBalance); // Инициализируем компонент
        textRealBalance.setText(""+ segmentBalance);// задаём текст
        textRealBalance.setTextColor(Color.rgb(0,0,0));//black color
        textRealBalance.setTextSize(TypedValue.COMPLEX_UNIT_MM, 5);
    }
}
