package es.ucm.fdi.gescom.roomdatabase;
//singleton
public class Usuario {
    private String mUserName;
    private int mId;
    private String mPassword;
    private String mRole;
    private static Usuario mUser;

    public static Usuario getUser(String username, String password){
        if(mUser == null){
            mUser = new Usuario(username, password);
            return mUser;
        }else return mUser;
    }

    private Usuario(String username, String password){
        mUserName = username;
        mPassword = password;
        mId = 0;
        mRole = "Vecino";
    }

    public String getUserName(){
        return mUserName;
    }

    public String Password(){
        return mPassword;
    }

    public int getId(){
        return mId;
    }

    public String getRole(){
        return mRole;
    }

}
