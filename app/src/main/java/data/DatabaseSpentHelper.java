package data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.calculator.poverty.dog.povertycalculator.money.ListMoney;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static data.Database.DB_PATH;

public class DatabaseSpentHelper extends Database {

    private static final String TABLE_SPENT = "spent";
    private static final String KEY_ID = "_id";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_THING = "thing";
    private static final String KEY_DATE = "date";
    private static final String KEY_MONEY = "money";
    private static final String KEY_FLAG = "flag";

    public DatabaseSpentHelper(Context context) {
        super(context);
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    public void addSpent(ListMoney listMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY, listMoney.getCategory());
        values.put(KEY_THING, listMoney.getThing());
        values.put(KEY_DATE, listMoney.getDate());
        values.put(KEY_MONEY, listMoney.getMoney());
        values.put(KEY_FLAG, listMoney.getSpent());
        db.insert(TABLE_SPENT, null, values);
        db.close();
    }

    public void addMoneyToBox(ListMoney listMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues barsikValues = new ContentValues();
// Помещаем значение "Clever" в колонку DESCRIPTION
        barsikValues.put(KEY_MONEY, listMoney.getMoney());
// Обновляем колонку DESCRIPTION с новым значением "Clever"
// в таблице WHERE NAME = "Barsik"
        db.update(TABLE_SPENT,
                barsikValues,
                "thing = ?",
                new String[] {listMoney.getThing() });
        db.close();
    }

    public int getSpentAllMoney(){
        int balance = 0;
        String selectQuery = "SELECT  * FROM " + TABLE_SPENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                balance += Integer.parseInt(cursor.getString(4));
            } while (cursor.moveToNext());
        }
        return balance;
    }

    public int getSpentNotAllMoney(){
        int balance = 0;
        String selectQuery = "SELECT  * FROM " + TABLE_SPENT + " WHERE " + KEY_FLAG + " IN ('true')";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                balance += Integer.parseInt(cursor.getString(4));
            } while (cursor.moveToNext());
        }
        return balance;
    }

    public ListMoney getSpent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SPENT, new String[] { KEY_ID,
                        KEY_CATEGORY, KEY_THING, KEY_DATE, KEY_MONEY, KEY_FLAG}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        ListMoney listMoney = new ListMoney(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return listMoney;
    }

    public List<ListMoney> getAllSpent() {
        List<ListMoney> spentList = new ArrayList<ListMoney>();
        String selectQuery = "SELECT  * FROM " + TABLE_SPENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ListMoney listMoney = new ListMoney();
                listMoney.setID(Integer.parseInt(cursor.getString(0)));
                listMoney.setCategory(cursor.getString(1));
                listMoney.setThing(cursor.getString(2));
                listMoney.setDate(cursor.getString(3));
                listMoney.setMoney(cursor.getString(4));
                listMoney.setSpent(cursor.getString(5));
                spentList.add(listMoney);
            } while (cursor.moveToNext());
        }
        return spentList;
    }

    public ArrayList<ListMoney> getThingNotSpent(){
        ArrayList<ListMoney> list = new  ArrayList();
        ListMoney listMoney;
        String selectQuery = "SELECT  * FROM " + TABLE_SPENT + " WHERE " + KEY_FLAG + " IN ('false')";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                listMoney = new ListMoney(cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
                list.add(listMoney);
            } while (cursor.moveToNext());
        }
        return list;
    }


    public int getBalance (){
        return  0;
    }

}