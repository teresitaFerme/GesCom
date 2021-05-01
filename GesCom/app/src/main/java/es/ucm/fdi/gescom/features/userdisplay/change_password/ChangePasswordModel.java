package es.ucm.fdi.gescom.features.userdisplay.change_password;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class ChangePasswordModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;

    ChangePasswordModel(Context ctx){
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(ctx);
    }


    public boolean changePassword(String pass, String newPass) {
        SQLiteDatabase db = mCommunitiesDBHelper.getWritableDatabase();

        String[] projection = {
                BaseColumns._ID,
                CommunitiesDatabase.User.COLUMN_NAME_PASSWORD
        };
        String selection = CommunitiesDatabase.User.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = {GesComApp.getUser().getUserName()};
        Cursor cursor = db.query(
                CommunitiesDatabase.User.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        if(cursor.moveToFirst()){
            if(cursor.getString(1).equals(pass)){
                ContentValues values = new ContentValues();
                values.put(CommunitiesDatabase.User.COLUMN_NAME_PASSWORD, newPass);

                db.update(
                        CommunitiesDatabase.User.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs
                );

                GesComApp.getUser().setmPassword(newPass);
                return true;
            } else return false;
        } else return false;


    }
}
