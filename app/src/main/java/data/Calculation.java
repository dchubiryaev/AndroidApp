package data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.calculator.poverty.dog.povertycalculator.money.ListGotMoney;
import com.calculator.poverty.dog.povertycalculator.money.ListMoney;

import java.io.IOException;

/**
 * Created by DoG on 14.10.2017.
 */

public class Calculation {

    private DatabaseGotMoneyHelper getMoneyHelper;
    private SQLiteDatabase sqLiteDatabaseGetMoneyHelper;
    private DatabaseSpentHelper dataSpentHelper;
    private SQLiteDatabase sqLiteDatabaseSpentHelper;

    public Calculation (){
    }

    public Calculation (Context context) {
        //ADD DATABASE
        getMoneyHelper = new DatabaseGotMoneyHelper(context);
        dataSpentHelper = new DatabaseSpentHelper(context);
        try {
            getMoneyHelper.updateDataBase();
            dataSpentHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            sqLiteDatabaseGetMoneyHelper = getMoneyHelper.getWritableDatabase();
            sqLiteDatabaseSpentHelper = dataSpentHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
    }

    public int getSumEarnedMoney(){
        return getMoneyHelper.getSumEarnedMoney();
    }

    public int getSpentAllMoney(){
        return dataSpentHelper.getSpentAllMoney();
    }

    public int getSpentNotAllMoney(){
        return dataSpentHelper.getSpentNotAllMoney();
    }

    public int getBalance(){
        return getSumEarnedMoney()-getSpentNotAllMoney();
    }

    public int getBalanceForSpenting(){
        return getSumEarnedMoney()-getSpentAllMoney();
    }

    public int getSpentMoneyTrue(){
        return 0;
    }

    public void addSpent(ListMoney listMoney) {
        dataSpentHelper.addSpent(listMoney);
    }

    public void addGot (ListGotMoney listGotMoney) {
        getMoneyHelper.addGot(listGotMoney);
    }

    public String getNumber() {



//        db.addSpent(new ListMoney("food", "apple", "14.10.2017", "30", "true"));

//        List<ListMoney> listMoneys = db.getAllSpent();
//        for (ListMoney cn : listMoneys) {
//            String log = "Id: "+cn.getID()+" ,Money: " + cn.getMoney() + " ,Thing: " + cn.getThing();
//            System.out.print("Name: ");
//            System.out.println(log);
//        }
        return "2";
    }
}
