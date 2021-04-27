package es.ucm.fdi.gescom.features.votaciones;

import android.content.Context;

import es.ucm.fdi.gescom.base.BasePresenter;

public class VotacionesPresenter extends BasePresenter {
    private VotacionesView mView;
    private VotacionesModel mModel;

    VotacionesPresenter(VotacionesView view){
        mView = view;
        mModel = new VotacionesModel((Context) view);
    }
}
