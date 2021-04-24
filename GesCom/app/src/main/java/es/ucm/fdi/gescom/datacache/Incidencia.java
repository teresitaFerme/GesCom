package es.ucm.fdi.gescom.datacache;

public class Incidencia {
    private String mAsunto;
    private String mDescripcion;
    private long mUsuario;
    private long mComunidad;

    public Incidencia(String asunto, String descripcion, long usuario, long comunidad){
        mAsunto = asunto;
        mDescripcion = descripcion;
        mUsuario = usuario;
        mComunidad = comunidad;
    }

    public String getAsunto(){return mAsunto;}

    public String getDescripcion(){return mDescripcion;}

    public long getmUsuario(){return mUsuario;}

    public long getmComunidad(){return mComunidad;}
}
