package es.ucm.fdi.gescom.features.avisos;

import es.ucm.fdi.gescom.base.BasePresenter;

public class AvisosPresenter extends BasePresenter {
    private AvisosView  mView;
    private AvisosModel mModel;

    AvisosPresenter(AvisosView view){
        mView = view;
        mModel = new AvisosModel();
    }
}
