package es.ucm.fdi.gescom.features.votaciones;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.datacache.Votacion;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class VotacionesModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;

    VotacionesModel(Context ctx){
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(ctx);
    }

    public ArrayList<Votacion> getVotacionesPendientes() {
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
        for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()) {
            if(cursor.getInt(5) != 0){
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
            }
        }

        return list;
    }

    public ArrayList<Votacion> getVotacionesPrevias() {
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
        for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()) {
            if(cursor.getInt(5) == 0){
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
            }
        }
        return list;
    }
}
