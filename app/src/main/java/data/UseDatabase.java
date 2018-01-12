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

public class UseDatabase {

    private DatabaseEarnHelper earnHelper;
    private SQLiteDatabase sqLiteDatabaseEarnHelper;
    private DatabaseSpentHelper spentHelper;
    private SQLiteDatabase sqLiteDatabaseSpentHelper;
    private DatabaseTotalHelper totalHelper;
    private SQLiteDatabase sqLiteDatabaseTotalHelper;

    public UseDatabase(Context context) {
        //ADD DATABASE
        earnHelper = new DatabaseEarnHelper(context);
        spentHelper = new DatabaseSpentHelper(context);
        totalHelper = new DatabaseTotalHelper(context);
        try {
            earnHelper.updateDataBase();
            spentHelper.updateDataBase();
            totalHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            sqLiteDatabaseEarnHelper = earnHelper.getWritableDatabase();
            sqLiteDatabaseSpentHelper = spentHelper.getWritableDatabase();
            sqLiteDatabaseTotalHelper = totalHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
    }

    public int getBalance(){ return totalHelper.getBalance();}

    public int getSumMoneyInMoneyBox(){ return spentHelper.getSumBalanceInMoneyBox();}

//    public int getSegment(){ return totalHelper.getSegment();}

    public int getSumEarnedMoney(){
        return earnHelper.getSumEarnedMoney();
    }

    public int getSumSpentMoney(){
        return spentHelper.getSumAllMoney();
    }

    public int getSpentNotAllMoney(){
        return spentHelper.getSumMoneyWhereFlagIsTrue();
    }

//    public int getBalance(){
//        return getSumEarnedMoney()-getSumMoneyWhereFlagIsTrue();
//    }

    public int getBalanceForSpenting(){
        return getSumEarnedMoney()- getSumSpentMoney();
    }

    public int getSpentMoneyTrue(){
        return 0;
    }

    public void deleteListFromMoneyBox(ListMoney listMoney){
        spentHelper.deleteListFromMoneyBox(listMoney);
    }

    public void addSpent(ListMoney listMoney) {
        spentHelper.addSpentMoneyToDB(listMoney);
        balanceMinusMoney(listMoney.getMoney());
    }

    public void addGot (ListGotMoney listGotMoney) {
        earnHelper.addEarnMoneyToDB(listGotMoney);
        balancePlusMoney(listGotMoney.getMoney());
    }

    public void balancePlusMoney(String money){
        totalHelper.BalancePlusMoney(money);
    }

    public void balanceMinusMoney(String money){
        totalHelper.BalanceMinusMoney(money);
    }

    public void updateListInMoneyBox(ListMoney listMoney){
        spentHelper.updateListInMoneyBoxToDB(listMoney);
    }


    public String getNumber() {



//        db.addSpentMoneyToDB(new ListMoney("food", "apple", "14.10.2017", "30", "true"));

//        List<ListMoney> listMoneys = db.getAllSpent();
//        for (ListMoney cn : listMoneys) {
//            String log = "Id: "+cn.getID()+" ,Money: " + cn.getMoney() + " ,Thing: " + cn.getComment();
//            System.out.print("Name: ");
//            System.out.println(log);
//        }
        return "2";
    }
}
