package es.ucm.fdi.gescom.datacache;

public class GesComApp {
    private static User mUser = null;
    private static Comunidad mComunidad = null;
    private static GesComApp mApp = null;

    public static GesComApp getApp(){
        if(mApp == null){
            mApp = new GesComApp();
        }
        return mApp;
    }

    public void setUser(String username, String password, String localizer, int id){
       mUser =  User.getInstance(username, password, localizer, id);
    }

    public static User getUser(){
        return mUser;
    }

    public static void setUser(User user){mUser = user;}

    public static Comunidad getComunidad(){return mComunidad;}

    public static void setComunidad(Comunidad comunidad){mComunidad = comunidad;}


}
