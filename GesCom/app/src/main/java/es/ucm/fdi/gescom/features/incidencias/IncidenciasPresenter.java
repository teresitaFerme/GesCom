package es.ucm.fdi.gescom.features.incidencias;

import android.content.Context;

import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.datacache.User;

public class IncidenciasPresenter extends BasePresenter {
    private IncidenciasView mIncidenciasView;
    private IncidenciasModel mIncidenciasModel;

    IncidenciasPresenter(IncidenciasView view){
        mIncidenciasModel = new IncidenciasModel((Context) view);
        mIncidenciasView = view;
    }

    public void validateIncidence(String asunto, String descripcion) {
        if(asunto.length() != 0 && descripcion.length() != 0){
            if(mIncidenciasModel.saveIncidence(asunto, descripcion, User.getUser(null,null, null).getId())){
                mIncidenciasView.reportSuccessful();
            }
            else{
                mIncidenciasView.reportServerFailure();
            }
        }else{
            mIncidenciasView.reportIncomplete();
        }
    }
}
