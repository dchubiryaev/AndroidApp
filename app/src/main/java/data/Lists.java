package data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;

/**
 * Created by DoG on 15.10.2017.
 */

public class Lists {

    private DatabaseSpinnerHelper spinnerHelper;
    private SQLiteDatabase sqLiteDatabase;

    public Lists(){
    }

    public Lists (Context context){
        spinnerHelper = new DatabaseSpinnerHelper(context);
        try{
            spinnerHelper.updateDataBase();
        } catch (IOException e) {
            throw new Error("UnableToUpdateDatabase");
        }
        try{
            sqLiteDatabase = spinnerHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
    }

    public String[] getCategorySpent () {
        return spinnerHelper.getCategorySpent();
    }

    public String[] getCategoryGot () {
        return spinnerHelper.getCategoryGot();
    }

}
