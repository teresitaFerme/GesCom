package es.ucm.fdi.gescom.datacache;

public class GesComApp {
    private static User mUser = null;
    private Comunidad mComunidad = null;
    private static GesComApp mApp = null;
    private static Incidencia mIncidencia;

    public static GesComApp getApp(){
        if(mApp == null){
            mApp = new GesComApp();
        }
        return mApp;
    }

    public void setUser(String username, String password){
       mUser =  User.getUser(username, password);
    }

    public static User getUser(){
        return mUser;
    }

    public static void setIncidencia(Incidencia incidencia){
        mIncidencia = incidencia;
    }

    public static Incidencia getIncidencia(){return mIncidencia;}


}
