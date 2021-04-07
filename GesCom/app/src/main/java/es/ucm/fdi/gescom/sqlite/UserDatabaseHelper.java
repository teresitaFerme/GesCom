package es.ucm.fdi.gescom.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UserDatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "User.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserDatabase.User.TABLE_NAME + " (" +
                    UserDatabase.User._ID + " INTEGER PRIMARY KEY," +
                    UserDatabase.User.COLUMN_NAME_USERNAME + " TEXT NOT NULL," +
                    UserDatabase.User.COLUMN_NAME_COMMUNITY + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserDatabase.User.TABLE_NAME;

    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
