package es.ucm.fdi.gescom.features.avisos.add_aviso;


import android.content.Context;

import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.features.reportar_incidencia.ReportarIncidenciaModel;
import es.ucm.fdi.gescom.features.reportar_incidencia.ReportarIncidenciaView;

public class AddAvisoPresenter extends BasePresenter {
    private AddAvisoView mAddAvisoView;
    private AddAvisoModel mAddAvisoModel;

    AddAvisoPresenter(AddAvisoView view){
        mAddAvisoModel = new AddAvisoModel((Context) view);
        mAddAvisoView = view;
    }

    public void validateIncidence(String asunto, String descripcion) {
        if(asunto.length() != 0 && descripcion.length() != 0){
            if(mAddAvisoModel.saveIncidence(asunto, descripcion, GesComApp.getUser().getId())){
                mAddAvisoView.reportSuccessful();
            }
            else{
                mAddAvisoView.reportServerFailure();
            }
        }else{
            mAddAvisoView.reportIncomplete();
        }
    }

}
