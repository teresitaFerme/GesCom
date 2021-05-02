package es.ucm.fdi.gescom.features.userdisplay;

import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.datacache.User;

public class UserDisplayPresenter extends BasePresenter {
    private UserDisplayView mView;
    private UserDisplayModel mModel;

    UserDisplayPresenter(UserDisplayView view){
        mView = view;
        mModel = new UserDisplayModel();
    }

    public void getUser() {
        User user = GesComApp.getUser();
        mView.setUserInfo(String.valueOf(user.getUserName()) , String.valueOf(user.getLocalizer()), GesComApp.getComunidad().getName() );
    }

    public void logOut() {
        mView.logOut();
    }

    public void changePassword() {
        mView.changePassword();
    }

    public boolean checkAdmin() {
        if(GesComApp.getUser().getLocalizer().equals("Administrador")) return true;
        else return false;
    }
}
