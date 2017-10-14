package data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.constraint.solver.ArrayLinkedVariables;

import java.util.ArrayList;

/**
 * Created by DoG on 15.10.2017.
 */

public class DatabaseSpinnerHelper extends Database  {

    private static final String TABLE_CATEGORY = "category";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";

    public DatabaseSpinnerHelper(Context context) {
        super(context);
    }

    public String[] getCategory(){
        ArrayList<String> category = new  ArrayList();

        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY;

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
