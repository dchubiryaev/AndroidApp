package com.calculator.poverty.dog.povertycalculator.money.moneybox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.calculator.poverty.dog.povertycalculator.R;
import com.calculator.poverty.dog.povertycalculator.money.CheckingCorrect;
import com.calculator.poverty.dog.povertycalculator.money.ListMoney;
import com.calculator.poverty.dog.povertycalculator.money.Money;
import com.calculator.poverty.dog.povertycalculator.money.spent.Spent;

import java.util.Date;

import data.UseDatabase;
import data.Lists;

/**
 * Created by DoG on 14.10.2017.
 */

public class SpentFromMoneyBox extends AppCompatActivity {

    private String choseCategory;
    private String money;
    private String comment;
    private Date date;

    private String thingFromPreview;
    private String moneyFromPreview;
    private String categoryFromPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spentfrommoneybox);
        getRowsFromBeforeView();
        setTextView();
        setSpinner();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent intent = new Intent(this, Money.class);
        startActivity(intent);
    }

    public void getRowsFromBeforeView(){
        Intent intent = getIntent();
        thingFromPreview = intent.getStringExtra("comment");
        moneyFromPreview = intent.getStringExtra("money");
        categoryFromPreview = intent.getStringExtra("category");
    }

    public void setTextView(){
        TextView spentMoneyText = (TextView)findViewById(R.id.setSpentMoneyText);
        spentMoneyText.setText("" + moneyFromPreview);
        TextView spentMoneyCommentText = (TextView)findViewById(R.id.setSpentMoneyCommentText);
        spentMoneyCommentText.setText("" + thingFromPreview);
    }

    public void setSpinner(){
        final Lists category = new Lists(this);
        String[] data = category.getCategorySpent();
        int position = 0;
        for (int x = 0; x < data.length; x++){
            if (data[x].equals(categoryFromPreview)){
                position = x;
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinnerstart, R.id.categoryname, data);
        adapter.setDropDownViewResource(R.layout.spinnerdropdown);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(position);
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

    public void spentButton (View view) {
        setData();

        if(!checkCorrectData()){
            return;
        }
        ListMoney spendList = new ListMoney(choseCategory, comment, date.toString(), money, "true");
        int computation = Integer.parseInt(moneyFromPreview)-Integer.parseInt(money);
        ListMoney ListInMoneyBox = new ListMoney(categoryFromPreview, thingFromPreview, date.toString(), computation +"", "false");
        UseDatabase useDatabase = new UseDatabase(this);
        useDatabase.addSpent(spendList);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        if(checkBox.isChecked()){
            useDatabase.deleteListFromMoneyBox(ListInMoneyBox);
        } else {
            useDatabase.updateListInMoneyBox(ListInMoneyBox);
        }
        Intent intent = new Intent(this, Money.class);
        startActivity(intent);
    }

    private void setData() {
        TextView getMoney = (TextView) findViewById(R.id.setSpentMoneyText);
        money = getMoney.getText().toString();
        TextView getComment = (TextView) findViewById(R.id.setSpentMoneyCommentText);
        comment = getComment.getText().toString();
        date = new Date();
    }

    public boolean checkCorrectData(){
        CheckingCorrect check = new CheckingCorrect();
        if (!check.checkNumber(money)){
            openQuitDialog();
            return false;
        }
        if (!check.checkComment(comment)){
            openQuitDialogComment();
            return false;
        }
        if(Integer.parseInt(moneyFromPreview) < Integer.parseInt(money)){
            openQuitDialogMoney();
            return false;
        }
        return true;
    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(this);
        quitDialog.setTitle("Enter correct numbers!");
        System.out.println("bla bla");

        quitDialog.setNegativeButton("Ok!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
            }
        });
        quitDialog.show();
    }

    private void openQuitDialogMoney() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(this);
        quitDialog.setTitle("Money can not be more than : " + moneyFromPreview + ". You can increase this in money box.");

        quitDialog.setNegativeButton("Ok!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        quitDialog.show();
    }

    public void addSpentToDataBase(){
        ListMoney listMoney = new ListMoney(choseCategory, comment, date.toString(), money, "true");
        UseDatabase useDatabase = new UseDatabase(this);
        useDatabase.addSpent(listMoney);
    }
}
