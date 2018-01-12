package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.calculator.poverty.dog.povertycalculator.money.ListGotMoney;
import com.calculator.poverty.dog.povertycalculator.money.ListMoney;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DoG on 14.10.2017.
 */

public class DatabaseTotalHelper extends Database {

    private static final String TABLE_NAME = "totals";
    private static final String KEY_ID = "_id";
    private static final String KEY_BALANCE = "balance";
    private static final String KEY_SEGMENT = "segment";

    public DatabaseTotalHelper(Context context) {
        super(context);
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    public int getBalance (){
        String selectQuery = "SELECT " + KEY_BALANCE + " FROM " + TABLE_NAME + " WHERE " + KEY_ID + " IN ('1')";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        return Integer.parseInt(cursor.getString(0));
    }

    public int getSegment (){
        String selectQuery = "SELECT " + KEY_SEGMENT + " FROM " + TABLE_NAME + " WHERE " + KEY_ID + " IN ('1')";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        return Integer.parseInt(cursor.getString(0));
    }

    public void BalancePlusMoney(String money) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int balance = getBalance();
        int gotMoney = Integer.parseInt(money);
        int totalBalance = balance + gotMoney;
        values.put(KEY_BALANCE, totalBalance);
        db.update(TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(1)});
        db.close();
    }

    public void BalanceMinusMoney(String money) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int balance = getBalance();
        int spentMoney = Integer.parseInt(money);
        int totalBalance = balance - spentMoney;
        values.put(KEY_BALANCE, totalBalance);
        db.update(TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(1)});
        db.close();
    }


}
