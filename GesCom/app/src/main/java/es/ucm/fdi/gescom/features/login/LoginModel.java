package es.ucm.fdi.gescom.features.login;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class LoginModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;

    LoginModel(Context ctx){
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(ctx);
    }

    public boolean validateLogin(String username, String password) {
        //TODO guardar todos los datos ne cache, incluida la comunidad entera

        SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                CommunitiesDatabase.User.COLUMN_NAME_USERNAME,
                CommunitiesDatabase.User.COLUMN_NAME_PASSWORD
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = CommunitiesDatabase.User.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = {username};

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
            if(cursor.getString(cursor.getColumnIndex(CommunitiesDatabase.User.COLUMN_NAME_PASSWORD)).equals(password)){
                GesComApp.getApp().setUser(username, password);;//aqui deberiamos meterle los datos reales
                return true;
            }
            else return false;
        } else return false;
    }
}
