package es.ucm.fdi.gescom.features.principal;

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

public class PrincipalModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;

    PrincipalModel(Context ctx){
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
                CommunitiesDatabase.Incidences.COLUMN_NAME_COMMUNITY_ID
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
        if(cursor.moveToFirst()){
            while(i < 5 && i < cursor.getCount()){
                String title  = cursor.getString(cursor.getColumnIndex(CommunitiesDatabase.Incidences.COLUMN_NAME_TITLE));
                String body = cursor.getString(cursor.getColumnIndex(CommunitiesDatabase.Incidences.COLUMN_NAME_BODY));
                long user_id = cursor.getLong(cursor.getColumnIndex(CommunitiesDatabase.Incidences.COLUMN_NAME_USER));
                long comm_id = cursor.getLong(cursor.getColumnIndex(CommunitiesDatabase.Incidences.COLUMN_NAME_COMMUNITY_ID));
                Incidencia incidencia = new Incidencia(title,
                        body,
                        user_id,
                        comm_id);
                list.add(incidencia);
                if(cursor.moveToNext()){
                    cursor.move(1);
                    i++;
                }
                else break;
            }
        }
        return list;
    }
}
