package es.ucm.fdi.gescom.features.incidencias;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.roomdatabase.GesComApp;
import es.ucm.fdi.gescom.roomdatabase.Incidencia;

public class IncidenciasModel extends BaseModel {
    public boolean saveIncidence(String asunto, String descripcion, int id) {
        //aqui se guarda la incidencia en la comunidad para que la vea el admin
        GesComApp.setIncidencia(new Incidencia(asunto, descripcion));
        return true;
    }
}
