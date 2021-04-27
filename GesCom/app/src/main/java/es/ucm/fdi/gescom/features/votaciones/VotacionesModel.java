package es.ucm.fdi.gescom.features.votaciones;

import android.content.Context;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class VotacionesModel extends BaseModel {
    private final CommunitiesDatabaseHelper mCommunitiesDBHelper;

    VotacionesModel(Context ctx){
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(ctx);
    }
}
