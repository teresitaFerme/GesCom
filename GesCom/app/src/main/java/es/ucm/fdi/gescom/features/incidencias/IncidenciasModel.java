package es.ucm.fdi.gescom.features.incidencias;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.datacache.Incidencia;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class IncidenciasModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;

    IncidenciasModel(Context ctx){
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(ctx);
    }

    public ArrayList<Incidencia> getIncidences() {
        ArrayList<Incidencia> list = new ArrayList<>();

        SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                CommunitiesDatabase.Incidences.COLUMN_NAME_TITLE,
                CommunitiesDatabase.Incidences.COLUMN_NAME_BODY,
                CommunitiesDatabase.Incidences.COLUMN_NAME_USER,
                CommunitiesDatabase.Incidences.COLUMN_NAME_COMMUNITY_ID,
                CommunitiesDatabase.Incidences.COLUMN_NAME_DATE,
                CommunitiesDatabase.Incidences.COLUMN_NAME_SEEN,
                CommunitiesDatabase.Incidences.COLUMN_NAME_USERNAME
        };
        String selection = CommunitiesDatabase.Incidences.COLUMN_NAME_COMMUNITY_ID + " = ?";
        String[] selectionArgs = {String.valueOf(GesComApp.getComunidad().getId())};
        Cursor cursor = db.query(
                CommunitiesDatabase.Incidences.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        int count = cursor.getCount();
        for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()) {
            String title  = cursor.getString(1);
            String body = cursor.getString(2);
            long user_id = cursor.getLong(3);
            long comm_id = cursor.getLong(4);
            String date = cursor.getString(5);
            String seen = cursor.getString(6);
            String username = cursor.getString(7);
            Incidencia incidencia = new Incidencia(title,
                    body,
                    user_id,
                    comm_id,
                    date,
                    seen,
                    username,
                    cursor.getInt(0));
            list.add(incidencia);
        }

        return list;
    }


    public ArrayList<Incidencia> getHisIncidences() {
        ArrayList<Incidencia> list = new ArrayList<>();

        SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                CommunitiesDatabase.Incidences.COLUMN_NAME_TITLE,
                CommunitiesDatabase.Incidences.COLUMN_NAME_BODY,
                CommunitiesDatabase.Incidences.COLUMN_NAME_USER,
                CommunitiesDatabase.Incidences.COLUMN_NAME_COMMUNITY_ID,
                CommunitiesDatabase.Incidences.COLUMN_NAME_DATE,
                CommunitiesDatabase.Incidences.COLUMN_NAME_SEEN,
                CommunitiesDatabase.Incidences.COLUMN_NAME_USERNAME
        };
        String selection = CommunitiesDatabase.Incidences.COLUMN_NAME_USER + " = ?";
        String[] selectionArgs = {String.valueOf(GesComApp.getUser().getId())};
        Cursor cursor = db.query(
                CommunitiesDatabase.Incidences.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        int count = cursor.getCount();
        for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()) {
            String title  = cursor.getString(1);
            String body = cursor.getString(2);
            long user_id = cursor.getLong(3);
            long comm_id = cursor.getLong(4);
            String date = cursor.getString(5);
            String seen = cursor.getString(6);
            String username = cursor.getString(7);
            Incidencia incidencia = new Incidencia(title,
                    body,
                    user_id,
                    comm_id,
                    date,
                    seen,
                    username,
                    cursor.getInt(0));
            list.add(incidencia);
        }
        return list;
    }
}
