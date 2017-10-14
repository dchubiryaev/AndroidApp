package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.calculator.poverty.dog.povertycalculator.money.ListGetMoney;
import com.calculator.poverty.dog.povertycalculator.money.ListMoney;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DoG on 14.10.2017.
 */

public class DatabaseGetMoneyHelper extends Database {

    private static final String TABLE_GET = "getMoney";
    private static final String KEY_ID = "_id";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_MONEY = "money";
    private static final String KEY_DATE = "date";

    public DatabaseGetMoneyHelper(Context context) {
        super(context);
    }


    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    public void addSpent(ListGetMoney listGetMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY, listGetMoney.getCategory());
        values.put(KEY_MONEY, listGetMoney.getDate());
        values.put(KEY_DATE, listGetMoney.getMoney());
        db.insert(TABLE_GET, null, values);
        db.close();
    }

    public int getSumEarnedMoney(){
        int balance = 0;
        List<ListMoney> spentList = new ArrayList<ListMoney>();
        String selectQuery = "SELECT  * FROM " + TABLE_GET;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                balance += Integer.parseInt(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        return balance;
    }

//    public ListGetMoney getSpent(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_SPENT, new String[] { KEY_ID,
//                        KEY_CATEGORY, KEY_DATE, KEY_MONEY,}, KEY_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//
//        if (cursor != null){
//            cursor.moveToFirst();
//        }
//
//        ListGetMoney listGetMoney = new ListGetMoney(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
//
//        return listGetMoney;
//    }
//
//    public List<ListMoney> getAllSpent() {
//        List<ListMoney> spentList = new ArrayList<ListMoney>();
//        String selectQuery = "SELECT  * FROM " + TABLE_SPENT;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                ListMoney listMoney = new ListMoney();
//                listMoney.setID(Integer.parseInt(cursor.getString(0)));
//                listMoney.setCategory(cursor.getString(1));
//                listMoney.setThing(cursor.getString(2));
//                listMoney.setDate(cursor.getString(3));
//                listMoney.setMoney(cursor.getString(4));
//                listMoney.setSpent(cursor.getString(5));
//                spentList.add(listMoney);
//            } while (cursor.moveToNext());
//        }
//        return spentList;
//    }
//

}
