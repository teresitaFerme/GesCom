package es.ucm.fdi.gescom.roomdatabase;
//singleton
public class Usuario {
    private String mUserName;
    private int mId;
    private String mPassword;
    private String role;
    private static Usuario mUser;

    public static Usuario getUser(String username, String password){
        if(mUser == null){
            return new Usuario(username, password);
        }else return mUser;
    }

    private Usuario(String username, String password){
        mUserName = username;
        mPassword = password;
        mId = 0;
        role = "user";
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

}
