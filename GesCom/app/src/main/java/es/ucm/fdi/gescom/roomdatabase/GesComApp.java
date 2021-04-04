package es.ucm.fdi.gescom.roomdatabase;

public class GesComApp {
    private static Usuario mUser = null;
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
       mUser =  Usuario.getUser(username, password);
    }

    public static Usuario getUser(){
        return mUser;
    }

    public static void setIncidencia(Incidencia incidencia){
        mIncidencia = incidencia;
    }

    public static Incidencia getIncidencia(){return mIncidencia;}


}
