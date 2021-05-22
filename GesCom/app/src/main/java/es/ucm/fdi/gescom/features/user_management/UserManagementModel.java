package es.ucm.fdi.gescom.features.user_management;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.datacache.User;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class UserManagementModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;


    public UserManagementModel(Context ctx){
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(ctx);
    }

    public ArrayList<User> retrieveCommunityUsers() {
        ArrayList<User> list = new ArrayList<>();

        SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                CommunitiesDatabase.User.COLUMN_NAME_USERNAME,
                CommunitiesDatabase.User.COLUMN_NAME_LOCALIZER,
                CommunitiesDatabase.User.COLUMN_NAME_DNI,
        };
        String selection = CommunitiesDatabase.User.COLUMN_NAME_COMMUNITY + " = ?";
        String[] selectionArgs = {String.valueOf(GesComApp.getComunidad().getName())};
        Cursor cursor = db.query(
                CommunitiesDatabase.User.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()) {
            String username  = cursor.getString(1);
            String localizer = cursor.getString(2);
            int user_id = cursor.getInt(0);
            String dni = cursor.getString(3);
            User user = new User(username,
                    null,
                    localizer,
                    user_id,
                    dni);
            list.add(user);
        }

        return list;
    }

    public boolean deleteUser(Integer petId) {
        SQLiteDatabase db = mCommunitiesDBHelper.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON");
        long newRowId = db.delete(CommunitiesDatabase.User.TABLE_NAME, BaseColumns._ID + "=?", new String[]{String.valueOf(petId)});

        if (newRowId == -1) return false;
        else return true;
    }
}