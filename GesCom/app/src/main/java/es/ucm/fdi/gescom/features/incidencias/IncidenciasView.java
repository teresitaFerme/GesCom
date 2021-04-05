package es.ucm.fdi.gescom.features.incidencias;

import es.ucm.fdi.gescom.base.BaseView;

public interface IncidenciasView extends BaseView {
    void reportIncomplete();
    void reportServerFailure();
    void reportSuccessful();
}
