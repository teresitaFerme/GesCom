package es.ucm.fdi.gescom.features.incidencias;

import es.ucm.fdi.gescom.base.BaseModel;

public class IncidenciasModel extends BaseModel {
    public boolean saveIncidence(String asunto, String descripcion, int id) {
        //aqui se guarda la incidencia en la comunidad para que la vea el admin
        return true;
    }
}
