package es.ucm.fdi.gescom.datacache;

public class Aviso {
    private String mAsunto;
    private String mDescripcion;
    private String date, hour;
    private long mComunidad;

    public Aviso(String asunto, String descripcion, long comunidad, String date, String hour){
        mAsunto = asunto;
        mDescripcion = descripcion;
        mComunidad = comunidad;
        this.date = date;
        this.hour = hour;
    }

    public String getAsunto(){return mAsunto;}

    public String getDescripcion(){return mDescripcion;}

    public long getmComunidad(){return mComunidad;}

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }
}
