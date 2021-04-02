package es.ucm.fdi.gescom.roomdatabase;

public class Usuario {
    private String mUserName;
    private int mId;
    private String mPassword;
    private String role;

    public Usuario(String username, String password){
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
