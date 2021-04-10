package es.ucm.fdi.gescom.datacache;
//singleton
public class User {
    private String mUserName;
    private int mId;
    private String mPassword;
    private String mRole;
    private static User mUser;

    public static User getUser(String username, String password){
        if(mUser == null){
            mUser = new User(username, password);
            return mUser;
        }else return mUser;
    }

    private User(String username, String password){
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
