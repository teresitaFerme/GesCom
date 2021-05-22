package es.ucm.fdi.gescom.datacache;
//singleton
public class User {
    private String mUserName;
    private int mId;
    private String mPassword;
    private String mLocalizer;
    private String mDNI;
    private static User mUser;

    public static User getInstance(String username, String password, String localizer, int id, String dni){
        if(mUser == null || !username.equals(mUser.getUserName()) ){
            mUser = new User(username, password, localizer, id, dni);
            return mUser;
        }else return mUser;
    }

    public User(String username, String password, String localizer, int id, String dni){
        mUserName = username;
        mPassword = password;
        mId = id;
        mLocalizer = localizer;
        mDNI = dni;
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

    public String getmDNI() {
        return mDNI;
    }

    public void setmDNI(String mDNI) {
        this.mDNI = mDNI;
    }
}
