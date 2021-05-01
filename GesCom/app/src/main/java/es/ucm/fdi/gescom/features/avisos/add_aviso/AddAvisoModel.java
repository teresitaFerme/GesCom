package es.ucm.fdi.gescom.features.avisos.add_aviso;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class AddAvisoModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;

    public AddAvisoModel(Context ctx) {
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(ctx);
    }

    public boolean saveIncidence(String asunto, String descripcion, int id) {
        SQLiteDatabase db = mCommunitiesDBHelper.getWritableDatabase();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        LocalDateTime now = LocalDateTime.now();

        ContentValues values = new ContentValues();
        values.put(CommunitiesDatabase.Avisos.COLUMN_NAME_COMMUNITY_ID, GesComApp.getComunidad().getId());
        values.put(CommunitiesDatabase.Avisos.COLUMN_NAME_TITLE, asunto);
        values.put(CommunitiesDatabase.Avisos.COLUMN_NAME_BODY, descripcion);
        values.put(CommunitiesDatabase.Avisos.COLUMN_NAME_DATE, String.valueOf(dtf.format(now)));
        values.put(CommunitiesDatabase.Avisos.COLUMN_NAME_HOUR, "hour");

        long newRowId = db.insert(CommunitiesDatabase.Avisos.TABLE_NAME, null, values);
        return newRowId != -1;
    }
}
