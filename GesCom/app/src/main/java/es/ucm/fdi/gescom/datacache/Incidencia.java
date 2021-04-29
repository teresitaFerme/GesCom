package es.ucm.fdi.gescom.datacache;

public class Incidencia {
    private String mAsunto;
    private String mDescripcion;
    private long mUsuario;
    private long mComunidad;
    private String date, hour;

    public Incidencia(String asunto, String descripcion, long usuario, long comunidad, String date, String hour){
        mAsunto = asunto;
        mDescripcion = descripcion;
        mUsuario = usuario;
        mComunidad = comunidad;
        this.date = date;
        this.hour = hour;
    }

    public String getAsunto(){return mAsunto;}

    public String getDescripcion(){return mDescripcion;}

    public long getmUsuario(){return mUsuario;}

    public long getmComunidad(){return mComunidad;}

    public String getHour() {
        return hour;
    }

    public String getDate() {
        return date;
    }
}
