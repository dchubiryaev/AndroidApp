package data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import com.calculator.poverty.dog.povertycalculator.money.ListMoney;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSpentHelper extends Database {

    private static final String TABLE_SPENT = "spent";
    private static final String KEY_ID = "_id";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_COMMENT = "thing";
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

    public void addSpentMoneyToDB(ListMoney listMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY, listMoney.getCategory());
        values.put(KEY_COMMENT, listMoney.getComment());
        values.put(KEY_DATE, listMoney.getDate());
        values.put(KEY_MONEY, listMoney.getMoney());
        values.put(KEY_FLAG, listMoney.getFlag());
        db.insert(TABLE_SPENT, null, values);
        db.close();
    }

    public void updateListInMoneyBoxToDB(ListMoney listMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_MONEY, listMoney.getMoney());
        // update: TABLE_NAME, VALUES, WHERE(thing] =, listMoney,getComment
        db.update(TABLE_SPENT, value, "flag = ? AND thing = ?", new String[] {"false", listMoney.getComment()});
        db.close();
    }

    public void deleteListFromMoneyBox(ListMoney listMoney){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SPENT, "flag = ? AND thing = ?", new String[] {"false", listMoney.getComment()});
        db.close();
    }

    //ПРОВЕРКА ПО THING ЕСЛИ БУДЕТ ДВА ОДИНАКОВЫх ПАРАМЕТРА БУДЕТ РАБОТАТЬ хУЙ ЗНАЕТ КАК!!!!
    public void setFlagTrueForThingInDB(ListMoney listMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_FLAG, "true");
        db.update(TABLE_SPENT, value, "flag = ? AND thing = ?", new String[] {"false", listMoney.getComment()});
        db.delete(TABLE_SPENT, "flag = ? AND thing = ?", new String[] {"false", listMoney.getComment()});
        db.close();
    }

    public int getSumBalanceInMoneyBox(){
        int balance = 0;
        String selectQuery = "SELECT  * FROM " + TABLE_SPENT + " WHERE " + KEY_FLAG + " IN ('false')";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                balance += Integer.parseInt(cursor.getString(4));
            } while (cursor.moveToNext());
        }
        return balance;
    }

    public int getSumAllMoney(){
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

    public int getSumMoneyWhereFlagIsTrue(){
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
                        KEY_CATEGORY, KEY_COMMENT, KEY_DATE, KEY_MONEY, KEY_FLAG}, KEY_ID + "=?",
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
                listMoney.setComment(cursor.getString(2));
                listMoney.setDate(cursor.getString(3));
                listMoney.setMoney(cursor.getString(4));
                listMoney.setFlag(cursor.getString(5));
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