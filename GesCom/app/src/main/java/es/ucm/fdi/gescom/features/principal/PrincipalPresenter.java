package es.ucm.fdi.gescom.features.principal;

import android.content.Context;

import java.util.ArrayList;

import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.datacache.Incidencia;

public class PrincipalPresenter extends BasePresenter {
    private PrincipalView mView;
    private PrincipalModel mModel;

    PrincipalPresenter(PrincipalView view){
        mView = view;
        mModel = new PrincipalModel((Context) view);
    }

    public ArrayList<Incidencia> getIncidencias() {
        ArrayList<Incidencia> list = mModel.getLastIncidences();
        return list;
    }
}
