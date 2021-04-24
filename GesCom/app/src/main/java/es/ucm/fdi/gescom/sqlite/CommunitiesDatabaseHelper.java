package es.ucm.fdi.gescom.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CommunitiesDatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GesCom.db";
    //CUIDADO CON NO PONER ESPACIOS DESPUES DE LA PRIMERA COMILLA
    private static final String SQL_CREATE_USER_TABLE =
            "CREATE TABLE " + CommunitiesDatabase.User.TABLE_NAME + " (" +
                    CommunitiesDatabase.User._ID + " INTEGER PRIMARY KEY," +
                    CommunitiesDatabase.User.COLUMN_NAME_USERNAME + " TEXT NOT NULL," +
                    CommunitiesDatabase.User.COLUMN_NAME_COMMUNITY + " TEXT NOT NULL," +
                    CommunitiesDatabase.User.COLUMN_NAME_PASSWORD + " TEXT NOT NULL," +
                    CommunitiesDatabase.User.COLUMN_NAME_DNI + " TEXT NOT NULL," +
                    CommunitiesDatabase.User.COLUMN_NAME_LOCALIZER + " TEXT NOT NULL);";

    private static final String SQL_CREATE_COMMUNITIES_TABLE =
            "CREATE TABLE " + CommunitiesDatabase.Communities.TABLE_NAME + " (" +
                    CommunitiesDatabase.Communities._ID + " INTEGER PRIMARY KEY," +
                    CommunitiesDatabase.Communities.COLUMN_NAME_NAME + " TEXT NOT NULL," +
                    CommunitiesDatabase.Communities.COLUMN_NAME_KEY + " TEXT, " +
                    CommunitiesDatabase.Communities.COLUMN_NAME_ID_ADMIN + " TEXT, FOREIGN KEY (" + CommunitiesDatabase.Communities.COLUMN_NAME_ID_ADMIN + ") REFERENCES " +
                    CommunitiesDatabase.User.TABLE_NAME + "(" + CommunitiesDatabase.User._ID + "));";

    private static final String SQL_CREATE_INCIDENCES_TABLE =
            "CREATE TABLE " + CommunitiesDatabase.Incidences.TABLE_NAME + " (" +
                    CommunitiesDatabase.Incidences._ID + " INTEGER PRIMARY KEY," +
                    CommunitiesDatabase.Incidences.COLUMN_NAME_TITLE + " TEXT NOT NULL," +
                    CommunitiesDatabase.Incidences.COLUMN_NAME_BODY + " TEXT NOT NULL," +
                    CommunitiesDatabase.Incidences.COLUMN_NAME_USER + " TEXT," +
                    CommunitiesDatabase.Incidences.COLUMN_NAME_COMMUNITY_ID + " TEXT, FOREIGN KEY (" +
                    CommunitiesDatabase.Incidences.COLUMN_NAME_USER + ") REFERENCES "+ CommunitiesDatabase.User.TABLE_NAME + "(" + CommunitiesDatabase.User._ID +"), FOREIGN KEY (" +
                    CommunitiesDatabase.Incidences.COLUMN_NAME_COMMUNITY_ID + ") REFERENCES " + CommunitiesDatabase.Communities.TABLE_NAME + "(" + CommunitiesDatabase.Communities._ID + "));";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CommunitiesDatabase.User.TABLE_NAME;

    public CommunitiesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER_TABLE);
        db.execSQL(SQL_CREATE_COMMUNITIES_TABLE);
        db.execSQL(SQL_CREATE_INCIDENCES_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
