package es.ucm.fdi.gescom.features.register_user.initialize_account;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.Random;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.datacache.Comunidad;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class InitAccountModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;

    InitAccountModel(Context ctx){
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(ctx);
    }


    public boolean getUsername(String username) {
        SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                CommunitiesDatabase.User.COLUMN_NAME_USERNAME
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

        return cursor.moveToFirst();
    }

    public void registerUser(String username, String pss, String dni) {
        SQLiteDatabase db = mCommunitiesDBHelper.getWritableDatabase();
        //TODO actualizar los datos en la bbdd

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
    }
}
