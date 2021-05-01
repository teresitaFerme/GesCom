package es.ucm.fdi.gescom.datacache;

public class Incidencia {
    private String mAsunto;
    private String mDescripcion;
    private long mUsuario;
    private long mComunidad;
    private String date;
    private boolean seen;
    private String username;
    private int _id;

    public Incidencia(String asunto, String descripcion, long usuario, long comunidad, String date, String seen, String username, int _id){
        mAsunto = asunto;
        mDescripcion = descripcion;
        mUsuario = usuario;
        mComunidad = comunidad;
        this.date = date;
        if(seen.equals("0")){
            this.seen = false;
        } this.seen = true;
        this.username = username;
        this._id = _id;
    }

    public String getAsunto(){return mAsunto;}

    public String getDescripcion(){return mDescripcion;}

    public long getmUsuario(){return mUsuario;}

    public long getmComunidad(){return mComunidad;}

    public String getDate() {
        return date;
    }

    public boolean getSeen(){return seen;}

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getUsername() {
        return username;
    }

    public int get_id() {
        return _id;
    }
}
