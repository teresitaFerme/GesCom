package es.ucm.fdi.gescom.features.votaciones.add_votacion;

import android.content.Context;

import es.ucm.fdi.gescom.base.BasePresenter;

public class AddVotacionPresenter extends BasePresenter {
    private AddVotacionView mView;
    private AddVotacionModel mModel;

    AddVotacionPresenter(AddVotacionView view){
        mView = view;
        mModel = new AddVotacionModel((Context) view);
    }

    public void addVotacion(String title, String description) {
        //TODO meter las validaciones de los campos
        if(mModel.addVotacion(title, description)){
            mView.addSuccessful();
        }
    }
}
