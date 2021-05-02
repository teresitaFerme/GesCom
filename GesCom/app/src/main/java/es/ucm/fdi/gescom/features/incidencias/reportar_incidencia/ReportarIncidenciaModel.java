package es.ucm.fdi.gescom.features.incidencias.reportar_incidencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

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

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        LocalDateTime now = LocalDateTime.now();

        ContentValues values = new ContentValues();
        values.put(CommunitiesDatabase.Incidences.COLUMN_NAME_COMMUNITY_ID, GesComApp.getComunidad().getId());
        values.put(CommunitiesDatabase.Incidences.COLUMN_NAME_USER, id);
        values.put(CommunitiesDatabase.Incidences.COLUMN_NAME_TITLE, asunto);
        values.put(CommunitiesDatabase.Incidences.COLUMN_NAME_BODY, descripcion);
        values.put(CommunitiesDatabase.Incidences.COLUMN_NAME_DATE, String.valueOf(dtf.format(now)));
        values.put(CommunitiesDatabase.Incidences.COLUMN_NAME_SEEN, "0");
        values.put(CommunitiesDatabase.Incidences.COLUMN_NAME_USERNAME, GesComApp.getUser().getUserName());

        long newRowId = db.insert(CommunitiesDatabase.Incidences.TABLE_NAME, null, values);
        return newRowId != -1;
    }
}
