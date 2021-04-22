package es.ucm.fdi.gescom.datacache;
//singleton
public class User {
    private String mUserName;
    private int mId;
    private String mPassword;
    private String mLocalizer;
    private static User mUser;

    public static User getUser(String username, String password, String localizer){
        if(mUser == null){
            mUser = new User(username, password, localizer);
            return mUser;
        }else return mUser;
    }

    public User(String username, String password, String localizer){
        mUserName = username;
        mPassword = password;
        mId = 0;
        mLocalizer = localizer;
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

    public String getLocalizer(){
        return mLocalizer;
    }

}
