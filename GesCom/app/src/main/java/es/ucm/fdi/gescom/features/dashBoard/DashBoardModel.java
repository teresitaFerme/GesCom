package es.ucm.fdi.gescom.features.dashBoard;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.datacache.Aviso;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.datacache.Incidencia;
import es.ucm.fdi.gescom.datacache.Votacion;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class DashBoardModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;

    DashBoardModel(Context ctx){
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(ctx);
    }

    public ArrayList<Incidencia> getLastIncidences() {
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
        int i = 0;
        int count = cursor.getCount();
        for (cursor.moveToLast(); !cursor.isBeforeFirst() && i < 5; cursor.moveToPrevious()) {
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
            i++;
        }
        cursor.close();
        return list;
    }

    public ArrayList<Votacion> getLastVotes() {
        ArrayList<Votacion> list = new ArrayList<>();

        SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                CommunitiesDatabase.Votes.COLUMN_NAME_TITLE,
                CommunitiesDatabase.Votes.COLUMN_NAME_VOTOS_FAVOR,
                CommunitiesDatabase.Votes.COLUMN_NAME_VOTOS_CONTRA,
                CommunitiesDatabase.Votes.COLUMN_NAME_DESCRIPTION,
                CommunitiesDatabase.Votes.COLUMN_NAME_OPENED
        };
        String selection = CommunitiesDatabase.Votes.COLUMN_NAME_COMMUNITY_ID + " = ?";
        String[] selectionArgs = {String.valueOf(GesComApp.getComunidad().getId())};
        Cursor cursor = db.query(
                CommunitiesDatabase.Votes.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        int i = 0;
        for (cursor.moveToLast(); !cursor.isBeforeFirst() && i < 5; cursor.moveToPrevious()) {
                String title  = cursor.getString(1);
                String description = cursor.getString(4);
                int votos_favor = cursor.getInt(2);
                int votos_contra = cursor.getInt(3);
                String opened = cursor.getString(5);
                Votacion votacion = new Votacion(cursor.getString(0),
                        title,
                        description,
                        votos_favor,
                        votos_contra,
                        opened);
                list.add(votacion);
                i++;
        }
        return list;
    }

    public ArrayList<Aviso> getLastAvisos() {
        ArrayList<Aviso> list = new ArrayList<>();

        SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                CommunitiesDatabase.Avisos.COLUMN_NAME_TITLE,
                CommunitiesDatabase.Avisos.COLUMN_NAME_BODY,
                CommunitiesDatabase.Avisos.COLUMN_NAME_DATE,
        };
        String selection = CommunitiesDatabase.Avisos.COLUMN_NAME_COMMUNITY_ID + " = ?";
        String[] selectionArgs = {String.valueOf(GesComApp.getComunidad().getId())};
        Cursor cursor = db.query(
                CommunitiesDatabase.Avisos.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        int i = 0;
        for (cursor.moveToLast(); !cursor.isBeforeFirst() && i < 5; cursor.moveToPrevious()) {
            String title  = cursor.getString(1);
            String body = cursor.getString(2);
            String date = cursor.getString(3);
            Aviso aviso = new Aviso(
                    title,
                    body,
                    0,
                    date,
                    null);
            list.add(aviso);
            i++;
        }
        cursor.close();
        return list;
    }
}
