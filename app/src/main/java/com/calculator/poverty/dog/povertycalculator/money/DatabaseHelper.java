package com.calculator.poverty.dog.povertycalculator.money;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

class DatabaseHelper extends SQLiteOpenHelper {
    //
    private static String DB_PATH; // полный путь к базе данных
   // private static final int SCHEMA = 1; // версия базы данных
    //
    private static final int DATABASE_VERSION = 5;
    private static String DB_NAME = "mydata";
    private static final String TABLE_SPENT = "spent2";
    private static final String KEY_ID = "_id";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_THING = "thing";
    private static final String KEY_DATE = "date";
    private static final String KEY_MONEY = "money";
    private static final String KEY_FLAG = "flag";

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        DB_PATH = context.getFilesDir().getPath() + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SPENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CATEGORY + " TEXT,"
                + KEY_THING + " TEXT," + KEY_DATE + " TEXT,"
                + KEY_MONEY + " TEXT," + KEY_FLAG + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPENT);
        onCreate(db);
    }

    public void addSpent(ListMoney listMoney) {
        System.out.println("in addSpent");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        System.out.println("put category");
        values.put(KEY_CATEGORY, listMoney.getCategory());
        values.put(KEY_THING, listMoney.getThing());
        values.put(KEY_DATE, listMoney.getDate());
        values.put(KEY_MONEY, listMoney.getMoney());
        values.put(KEY_FLAG, listMoney.getSpent());
        System.out.println("insert method");

        db.insert(TABLE_SPENT, null, values);
        db.close();
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


}
