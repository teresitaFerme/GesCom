package es.ucm.fdi.gescom.datacache;

public class GesComApp {
    private static User mUser = null;
    private static Comunidad mComunidad = null;
    private static GesComApp mApp = null;
    private static Incidencia mIncidencia;

    public static GesComApp getApp(){
        if(mApp == null){
            mApp = new GesComApp();
        }
        return mApp;
    }

    public void setUser(String username, String password, String localizer){
       mUser =  User.getUser(username, password, localizer);
    }

    public static User getUser(){
        return mUser;
    }

    public static void setIncidencia(Incidencia incidencia){
        mIncidencia = incidencia;
    }

    public static Incidencia getIncidencia(){return mIncidencia;}

    public static Comunidad getComunidad(){return mComunidad;}

    public static void setComunidad(Comunidad comunidad){mComunidad = comunidad;}


}
