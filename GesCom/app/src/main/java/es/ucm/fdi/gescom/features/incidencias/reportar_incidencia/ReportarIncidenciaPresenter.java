package es.ucm.fdi.gescom.features.incidencias.reportar_incidencia;

import android.content.Context;

import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.datacache.GesComApp;

public class ReportarIncidenciaPresenter extends BasePresenter {
    private ReportarIncidenciaView mIncidenciasView;
    private ReportarIncidenciaModel mIncidenciasModel;

    ReportarIncidenciaPresenter(ReportarIncidenciaView view){
        mIncidenciasModel = new ReportarIncidenciaModel((Context) view);
        mIncidenciasView = view;
    }

    public void validateIncidence(String asunto, String descripcion) {
        if(asunto.length() != 0 && descripcion.length() != 0){
            if(mIncidenciasModel.saveIncidence(asunto, descripcion, GesComApp.getUser().getId())){
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
