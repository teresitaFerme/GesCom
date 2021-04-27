package es.ucm.fdi.gescom.features.reportar_incidencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class ReportarIncidenciaModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;

    ReportarIncidenciaModel(Context ctx){
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(ctx);
    }

    public boolean saveIncidence(String asunto, String descripcion, int id) {
        SQLiteDatabase db = mCommunitiesDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CommunitiesDatabase.Incidences.COLUMN_NAME_COMMUNITY_ID, GesComApp.getComunidad().getId());
        values.put(CommunitiesDatabase.Incidences.COLUMN_NAME_USER, GesComApp.getUser().getId());
        values.put(CommunitiesDatabase.Incidences.COLUMN_NAME_TITLE, asunto);
        values.put(CommunitiesDatabase.Incidences.COLUMN_NAME_BODY, descripcion);

        long newRowId = db.insert(CommunitiesDatabase.Incidences.TABLE_NAME, null, values);
        return newRowId != -1;
    }
}
