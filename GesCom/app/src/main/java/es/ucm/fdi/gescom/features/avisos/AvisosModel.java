package es.ucm.fdi.gescom.features.avisos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.datacache.Aviso;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class AvisosModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;

    AvisosModel(Context ctx){
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(ctx);
    }


    public ArrayList<Aviso> getAvisos() {
        ArrayList<Aviso> list = new ArrayList<>();

        SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                CommunitiesDatabase.Avisos.COLUMN_NAME_TITLE,
                CommunitiesDatabase.Avisos.COLUMN_NAME_BODY,
                CommunitiesDatabase.Avisos.COLUMN_NAME_DATE,
                CommunitiesDatabase.Avisos.COLUMN_NAME_HOUR,
                CommunitiesDatabase.Avisos.COLUMN_NAME_COMMUNITY_ID
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

        for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()) {
            String id = cursor.getString(0);
            String title  = cursor.getString(1);
            String body = cursor.getString(2);
            String date = cursor.getString(3);
            String hour = cursor.getString(4);
            long comm_id = cursor.getLong(5);
            Aviso aviso = new Aviso(title,
                    body,
                    comm_id,
                    date,
                    hour,
                    id);
            list.add(aviso);
        }

        return list;
    }

    //TODO L- No se qué variable hay que pasar al model para especificar qué fila borar de la BBDD
    public void eliminarAviso() {


    }
}
