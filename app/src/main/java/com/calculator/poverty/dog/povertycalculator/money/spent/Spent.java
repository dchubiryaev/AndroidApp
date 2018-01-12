package com.calculator.poverty.dog.povertycalculator.money.spent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.calculator.poverty.dog.povertycalculator.R;
import com.calculator.poverty.dog.povertycalculator.money.CheckingCorrect;
import com.calculator.poverty.dog.povertycalculator.money.ListMoney;
import com.calculator.poverty.dog.povertycalculator.money.Money;

import java.util.Date;

import data.UseDatabase;
import data.Lists;

/**
 * Created by DoG on 14.10.2017.
 */

public class Spent extends AppCompatActivity {

    private String choseCategory;
    private String money;
    private String comment;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spent);
        setSpinner();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent intent = new Intent(this, Money.class);
        startActivity(intent);
    }


    public void setSpentMoney (View view) {
        TextView getMoney = (TextView) findViewById(R.id.setSpentMoneyText);
        money = getMoney.getText().toString();
        TextView getComment = (TextView) findViewById(R.id.setSpentMoneyCommentText);
        comment = getComment.getText().toString();

        CheckingCorrect check = new CheckingCorrect();
        if (check.checkNumber(getMoney.getText().toString())){
            money = getMoney.getText().toString();
            if (!check.checkComment(comment)){
                openQuitDialogComment();
            } else {
                date = new Date();
                ListMoney listMoney = new ListMoney(choseCategory, comment, date.toString(), money, "true");
                UseDatabase useDatabase = new UseDatabase(this);

                useDatabase.addSpent(listMoney);
                Intent intent = new Intent(Spent.this, Money.class);
                startActivity(intent);
            }
        }
        else {
            openQuitDialog();
        }


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

    private void openQuitDialogComment() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(this);
        quitDialog.setTitle("Comment can not be zero!");

        quitDialog.setNegativeButton("Ok!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });

        quitDialog.show();
    }

}
