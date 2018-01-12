package com.calculator.poverty.dog.povertycalculator.money.moneybox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.calculator.poverty.dog.povertycalculator.R;
import com.calculator.poverty.dog.povertycalculator.money.CheckingCorrect;
import com.calculator.poverty.dog.povertycalculator.money.ListMoney;
import com.calculator.poverty.dog.povertycalculator.money.Money;

import java.util.ArrayList;

import data.DatabaseSpentHelper;
import data.UseDatabase;

/**
 * Created by DoG on 15.10.2017.
 */

public class MoneyBox extends AppCompatActivity {

    ArrayList<ListMoney> data;
    String money;
    ListView lv;
    ListMoney listMoney;
    private UseDatabase useDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneybox);
        setListView();
        useDatabase = new UseDatabase(this);
        setTaxtBalance();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                listMoney = data.get(position);
            }
        });

        lv.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Intent intent=new Intent ( MoneyBox.this , SpentFromMoneyBox.class );
                listMoney = data.get(position);
                intent.putExtra("comment", listMoney.getComment());
                intent.putExtra("money", listMoney.getMoney());
                intent.putExtra("category", listMoney.getCategory());
                startActivity(intent);
                // Возвращает "истину", чтобы завершить событие клика, чтобы
                // onListItemClick больше не вызывался
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent intent = new Intent(this, Money.class);
        startActivity(intent);
    }

    public void setListView () {
        final DatabaseSpentHelper thing = new DatabaseSpentHelper(this);
        data = thing.getThingNotSpent();
        lv = (ListView) this.findViewById(R.id.listView);
        lv.setAdapter(new MoneyBoxAdapter(this,data));

    }

    public void addPositionToMoneyBox(View view){
        Intent intent = new Intent ( MoneyBox.this , AddPositionToMoneyBox.class );
        startActivity(intent);
    }

    public void addMoney(View view){
        final DatabaseSpentHelper thing = new DatabaseSpentHelper(this);
        TextView getMoney = (TextView) findViewById(R.id.setMoneyText);
        CheckingCorrect check = new CheckingCorrect();

        if (check.checkNumber(getMoney.getText().toString())){
            if (check.checkItem(listMoney)){
                int beforeMoney = Integer.parseInt(listMoney.getMoney());
                int addMoney = Integer.parseInt(getMoney.getText().toString());
                int sum = beforeMoney + addMoney;
                listMoney.setMoney(""+sum);
                thing.updateListInMoneyBoxToDB(listMoney);
                getMoney.setText("");
                recreate();
            } else {
                openQuitDialogItem();
            }
        }
        else {
            openQuitDialog();
        }


    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(this);
        quitDialog.setTitle("Enter correct numbers!");

        quitDialog.setNegativeButton("Ok!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });

        quitDialog.show();
    }

    private void openQuitDialogItem() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(this);
        quitDialog.setTitle("Checking Item!");

        quitDialog.setNegativeButton("Ok!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });

        quitDialog.show();
    }

    private void setTaxtBalance(){
        int balance = useDatabase.getBalance();

        TextView textBal = (TextView)findViewById(R.id.textBalance); // Инициализируем компонент
        textBal.setText("Really Balance:");// задаём текст
        textBal.setTextColor(Color.rgb(0,0,0));//black color
        textBal.setTextSize(TypedValue.COMPLEX_UNIT_MM, 5);
        TextView textBalance = (TextView)findViewById(R.id.textMoneyBalance); // Инициализируем компонент
        textBalance.setText("" + balance);// задаём текст
        textBalance.setTextColor(Color.rgb(0,0,0));//black color
        textBalance.setTextSize(TypedValue.COMPLEX_UNIT_MM, 5);
    }


}
