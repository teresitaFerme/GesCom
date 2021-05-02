package es.ucm.fdi.gescom.features.register_user.initialize_account;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.datacache.User;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class InitAccountModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;

    InitAccountModel(Context ctx){
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(ctx);
    }


    public boolean getUsername(String username) {
        SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                CommunitiesDatabase.User.COLUMN_NAME_USERNAME
        };

        String selection = CommunitiesDatabase.User.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(
                CommunitiesDatabase.User.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        return cursor.moveToFirst();
    }

    public void registerUser(String username, String pss, String dni) {
        SQLiteDatabase db = mCommunitiesDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CommunitiesDatabase.User.COLUMN_NAME_USERNAME, username);
        values.put(CommunitiesDatabase.User.COLUMN_NAME_PASSWORD, pss);

        String selection = CommunitiesDatabase.User.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = {dni};

        db.update(
                CommunitiesDatabase.User.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        String[] projection = {
                BaseColumns._ID,
                CommunitiesDatabase.User.COLUMN_NAME_LOCALIZER
        };
        selectionArgs = new String[]{username};
        GesComApp.setUser(new User(username, pss, db.query(CommunitiesDatabase.User.TABLE_NAME, projection, selection, selectionArgs, null, null, null).getString(1), db.query(CommunitiesDatabase.User.TABLE_NAME, projection, selection, selectionArgs, null, null, null).getInt(0)));
    }
}
