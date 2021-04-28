package es.ucm.fdi.gescom.features.votaciones.add_votacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class AddVotacionModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;

    AddVotacionModel(Context ctx){
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(ctx);
    }

    public boolean addVotacion(String title, String description) {
        SQLiteDatabase db = mCommunitiesDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CommunitiesDatabase.Votes.COLUMN_NAME_COMMUNITY_ID, GesComApp.getComunidad().getId());
        values.put(CommunitiesDatabase.Votes.COLUMN_NAME_TITLE, title);
        values.put(CommunitiesDatabase.Votes.COLUMN_NAME_DESCRIPTION, description);
        values.put(CommunitiesDatabase.Votes.COLUMN_NAME_OPENED, 1);
        values.put(CommunitiesDatabase.Votes.COLUMN_NAME_VOTOS_CONTRA, "0");
        values.put(CommunitiesDatabase.Votes.COLUMN_NAME_VOTOS_FAVOR, "0");

        long newRowId = db.insert(CommunitiesDatabase.Votes.TABLE_NAME, null, values);
        return newRowId != -1;
    }
}
