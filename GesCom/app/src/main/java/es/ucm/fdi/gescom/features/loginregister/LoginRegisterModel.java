package es.ucm.fdi.gescom.features.loginregister;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.datacache.Comunidad;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class LoginRegisterModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;

    LoginRegisterModel(Context ctx){
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(ctx);
    }

    public boolean validateLogin(String username, String password) {
        //TODO guardar todos los datos ne cache, incluida la comunidad entera

        SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                CommunitiesDatabase.User.COLUMN_NAME_USERNAME,
                CommunitiesDatabase.User.COLUMN_NAME_PASSWORD,
                CommunitiesDatabase.User.COLUMN_NAME_LOCALIZER,
                CommunitiesDatabase.User.COLUMN_NAME_COMMUNITY
        };
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
                GesComApp.getApp().setUser(username, password, cursor.getString(cursor.getColumnIndex(CommunitiesDatabase.User.COLUMN_NAME_LOCALIZER)));
                String communityName = cursor.getString(cursor.getColumnIndex(CommunitiesDatabase.User.COLUMN_NAME_COMMUNITY));
                projection = new String[]{
                        BaseColumns._ID,
                        CommunitiesDatabase.Communities._ID,
                        CommunitiesDatabase.Communities.COLUMN_NAME_NAME,
                        CommunitiesDatabase.Communities.COLUMN_NAME_ID_ADMIN,
                        CommunitiesDatabase.Communities.COLUMN_NAME_KEY
                };
                selection = CommunitiesDatabase.Communities.COLUMN_NAME_NAME + " = ?";
                selectionArgs = new String[]{communityName};
                cursor = db.query(
                        CommunitiesDatabase.Communities.TABLE_NAME,   // The table to query
                        projection,             // The array of columns to return (pass null to get all)
                        selection,              // The columns for the WHERE clause
                        selectionArgs,          // The values for the WHERE clause
                        null,                   // don't group the rows
                        null,                   // don't filter by row groups
                        null               // The sort order
                );
                if(cursor.moveToFirst()){
                    Comunidad comu = new Comunidad(cursor.getLong(cursor.getColumnIndex(CommunitiesDatabase.Communities._ID)),
                            cursor.getString(cursor.getColumnIndex(CommunitiesDatabase.Communities.COLUMN_NAME_NAME)),
                            cursor.getLong(cursor.getColumnIndex(CommunitiesDatabase.Communities.COLUMN_NAME_ID_ADMIN)),
                            cursor.getString(cursor.getColumnIndex(CommunitiesDatabase.Communities.COLUMN_NAME_KEY)));
                    GesComApp.setComunidad(comu);
                    return true;
                }else return false;
            }
            else return false;
        } else return false;
    }
}

