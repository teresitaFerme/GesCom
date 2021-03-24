package es.ucm.fdi.gescom.roomdatabase;

public class Usuario {
    private String mUserName;
    private int mId;
    private String mPassword;

    public Usuario(String username, String password){
        mUserName = username;
        mPassword = password;
        mId = 0;
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
