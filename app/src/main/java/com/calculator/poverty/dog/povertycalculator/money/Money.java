package com.calculator.poverty.dog.povertycalculator.money;

;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;
import com.calculator.poverty.dog.povertycalculator.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.IOException;
import java.util.List;

import data.Calculation;
import data.DatabaseGetMoneyHelper;
import data.DatabaseSpentHelper;

public class Money extends AppCompatActivity {

    private Calculation calculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        calculation = new Calculation(this);
        setText();
        setGraph();
        setButtonSpent();
    }

    private void setGraph() {
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3)
        });
        graph.addSeries(series);
    }

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

    private void setButtonSpent(){

    }




}
