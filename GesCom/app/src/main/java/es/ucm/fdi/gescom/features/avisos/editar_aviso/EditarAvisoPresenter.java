package es.ucm.fdi.gescom.features.avisos.editar_aviso;


import android.content.Context;

import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.datacache.GesComApp;

public class EditarAvisoPresenter extends BasePresenter {
    private EditarAvisoView mEditarAvisoView;
    private EditarAvisoModel mEditarAvisoModel;

    EditarAvisoPresenter(EditarAvisoView view){
        mEditarAvisoModel = new EditarAvisoModel((Context) view);
        mEditarAvisoView = view;
    }

    public void validateIncidence(String asunto, String descripcion) {
        if(asunto.length() != 0 && descripcion.length() != 0){
            if(mEditarAvisoModel.saveIncidence(asunto, descripcion, GesComApp.getUser().getId())){
                mEditarAvisoView.reportSuccessful();
            }
            else{
                mEditarAvisoView.reportServerFailure();
            }
        }else{
            mEditarAvisoView.reportIncomplete();
        }
    }

}
