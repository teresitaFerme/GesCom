package es.ucm.fdi.gescom.features.incidencias;

import android.content.Context;

import java.util.ArrayList;

import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.datacache.Incidencia;

public class IncidenciasPresenter extends BasePresenter {
    private IncidenciasView mView;
    private IncidenciasModel mModel;

    IncidenciasPresenter(IncidenciasView view){
        mView = view;
        mModel = new IncidenciasModel((Context) view);
    }

    public ArrayList<Incidencia> getIncidencias() {
        ArrayList<Incidencia> list = mModel.getIncidences();
        return list;
    }

    public void launchNewIncidence() {
        mView.launchNewIncidence();
    }
}
