package es.ucm.fdi.gescom.features.userdisplay;

import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.roomdatabase.Usuario;

public class UserDisplayPresenter extends BasePresenter {
    private UserDisplayView mView;
    private UserDisplayModel mModel;

    UserDisplayPresenter(UserDisplayView view){
        mView = view;
        mModel = new UserDisplayModel();
    }

    public void getUser() {
        Usuario user = Usuario.getUser(null, null);
        mView.setUserInfo(String.valueOf(user.getUserName()) , String.valueOf(user.getRole()) );
    }
}
