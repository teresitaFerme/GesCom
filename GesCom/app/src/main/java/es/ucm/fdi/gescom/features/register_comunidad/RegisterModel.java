package es.ucm.fdi.gescom.features.register_comunidad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.datacache.Comunidad;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class RegisterModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;

    RegisterModel(Context ctx){
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

    public boolean getCommunityName(String name) {
        SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = CommunitiesDatabase.Communities.COLUMN_NAME_NAME + " = ?";
        String[] selectionArgs = {name};

        Cursor cursor = db.query(
                CommunitiesDatabase.Communities.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        return cursor.moveToFirst();
    }

    public long registerUser(String username, String community, String pss, String dni, String localizer) {
        SQLiteDatabase db = mCommunitiesDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CommunitiesDatabase.User.COLUMN_NAME_COMMUNITY, community);
        values.put(CommunitiesDatabase.User.COLUMN_NAME_USERNAME, username);
        values.put(CommunitiesDatabase.User.COLUMN_NAME_PASSWORD, pss);
        values.put(CommunitiesDatabase.User.COLUMN_NAME_DNI, dni);
        values.put(CommunitiesDatabase.User.COLUMN_NAME_LOCALIZER, localizer);

        long newRowId = db.insert(CommunitiesDatabase.User.TABLE_NAME, null, values);

        return newRowId;
    }

    public boolean registerCommunity(String communityName, long idAdmin) {
        SQLiteDatabase db = mCommunitiesDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CommunitiesDatabase.Communities.COLUMN_NAME_NAME, communityName);
        values.put(CommunitiesDatabase.Communities.COLUMN_NAME_ID_ADMIN, idAdmin);

        int zero = 48;
        int letterZ = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedKey = random.ints(zero, letterZ + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        values.put(CommunitiesDatabase.Communities.COLUMN_NAME_KEY, generatedKey);

        long newRowId = db.insert(CommunitiesDatabase.Communities.TABLE_NAME, null, values);

        GesComApp.setComunidad(new Comunidad(newRowId, communityName, idAdmin, generatedKey));

        return newRowId != -1;
    }

}
