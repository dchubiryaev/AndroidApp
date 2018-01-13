package data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by DoG on 15.10.2017.
 */

public class DatabaseSpinnerHelper extends Database  {

    private static final String TABLE_CATEGORY = "category";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_STATUS = "status";

    public DatabaseSpinnerHelper(Context context) {
        super(context);
    }

    public String[] getCategorySpent(){
        ArrayList<String> category = new  ArrayList();

        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY + " WHERE " + KEY_STATUS + " IN ('spent')";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
             do {
                 if (cursor.getString(1).equals("Money Box")) {
                     cursor.moveToNext();
                 }
                 category.add(cursor.getString(1));
             } while (cursor.moveToNext());

        }
        int count = category.size();
        String[] arr = new String[count];
        for (int i = 0; i < count ; i++) {
            arr[i] = category.get(i);
        }
        return arr;
    }

    public String[] getCategoryGot(){
        ArrayList<String> category = new  ArrayList();

        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY + " WHERE " + KEY_STATUS + " IN ('got')";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                category.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        int count = category.size();
        String[] arr = new String[count];
        for (int i = 0; i < count ; i++) {
            arr[i] = category.get(i);
        }
        return arr;
    }
}
