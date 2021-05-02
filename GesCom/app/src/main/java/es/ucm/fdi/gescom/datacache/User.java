package es.ucm.fdi.gescom.datacache;
//singleton
public class User {
    private String mUserName;
    private int mId;
    private String mPassword;
    private String mLocalizer;
    private static User mUser;

    public static User getUser(String username, String password, String localizer, int id){
        if(mUser == null || !username.equals(mUser.getUserName()) ){
            mUser = new User(username, password, localizer, id);
            return mUser;
        }else return mUser;
    }

    public User(String username, String password, String localizer, int id){
        mUserName = username;
        mPassword = password;
        mId = id;
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

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
