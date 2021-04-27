package es.ucm.fdi.gescom.features.reportar_incidencia;

import es.ucm.fdi.gescom.base.BaseView;

public interface ReportarIncidenciaView extends BaseView {
    void reportIncomplete();
    void reportServerFailure();
    void reportSuccessful();
}
