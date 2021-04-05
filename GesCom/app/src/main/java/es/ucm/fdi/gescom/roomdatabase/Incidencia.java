package es.ucm.fdi.gescom.roomdatabase;

public class Incidencia {
    private String mAsunto;
    private String mDescripcion;

    public Incidencia(String asunto, String descripcion){
        mAsunto = asunto;
        mDescripcion = descripcion;
    }

    public String getAsunto(){return mAsunto;}

    public String getDescripcion(){return mDescripcion;}
}
