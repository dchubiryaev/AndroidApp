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

public class DatabaseEarnHelper extends Database {

    private static final String TABLE_NAME = "getMoney";
    private static final String KEY_ID = "_id";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_MONEY = "money";
    private static final String KEY_DATE = "date";

    public DatabaseEarnHelper(Context context) {
        super(context);
    }


    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    public int getSumEarnedMoney(){
        int balance = 0;
        List<ListMoney> spentList = new ArrayList<ListMoney>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                balance += Integer.parseInt(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        return balance;
    }

    public void addEarnMoneyToDB(ListGotMoney listMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY, listMoney.getCategory());
        values.put(KEY_DATE, listMoney.getDate());
        values.put(KEY_MONEY, listMoney.getMoney());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

//    public ListGetMoney getFlag(int id) {
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
//                listMoney.setComment(cursor.getString(2));
//                listMoney.setDate(cursor.getString(3));
//                listMoney.setMoney(cursor.getString(4));
//                listMoney.setFlag(cursor.getString(5));
//                spentList.add(listMoney);
//            } while (cursor.moveToNext());
//        }
//        return spentList;
//    }
//

}
