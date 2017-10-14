package com.calculator.poverty.dog.povertycalculator.money;

;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.calculator.poverty.dog.povertycalculator.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

import static com.calculator.poverty.dog.povertycalculator.R.drawable.money;

public class Money extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        setText();
        setGraph();
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
        textBalance.setText("150");// задаём текст
        textBalance.setTextColor(Color.rgb(0,0,0));//black color
        textBalance.setTextSize(TypedValue.COMPLEX_UNIT_MM, 5);

        TextView textRealBal = (TextView)findViewById(R.id.textRealBal); // Инициализируем компонент
        textRealBal.setText("Money for spent:");// задаём текст
        textRealBal.setTextColor(Color.rgb(0,0,0));//black color
        textRealBal.setTextSize(TypedValue.COMPLEX_UNIT_MM, 5);
        TextView textRealBalance = (TextView)findViewById(R.id.textRealBalance); // Инициализируем компонент
        textRealBalance.setText(getNumber());// задаём текст
        textRealBalance.setTextColor(Color.rgb(0,0,0));//black color
        textRealBalance.setTextSize(TypedValue.COMPLEX_UNIT_MM, 5);
    }

    public String getNumber() {

        DatabaseHelper db = new DatabaseHelper(this);

        db.addSpent(new ListMoney("food", "apple", "14.10.2017", "30", "true"));

        System.out.println("Inserting ..");

        System.out.println("Reading all contacts..");
        List<ListMoney> listMoneys = db.getAllSpent();
        for (ListMoney cn : listMoneys) {
            String log = "Id: "+cn.getID()+" ,Money: " + cn.getMoney() + " ,Thing: " + cn.getThing();
            System.out.print("Name: ");
            System.out.println(log);
        }
        return "2";
    }


}
