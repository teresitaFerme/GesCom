package es.ucm.fdi.gescom.features.incidencias;

import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.roomdatabase.User;

public class IncidenciasPresenter extends BasePresenter {
    private IncidenciasView mIncidenciasView;
    private IncidenciasModel mIncidenciasModel;

    IncidenciasPresenter(IncidenciasView view){
        mIncidenciasModel = new IncidenciasModel();
        mIncidenciasView = view;
    }

    public void validateIncidence(String asunto, String descripcion) {
        if(asunto.length() != 0 && descripcion.length() != 0){
            if(mIncidenciasModel.saveIncidence(asunto, descripcion, User.getUser(null,null).getId())){
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
