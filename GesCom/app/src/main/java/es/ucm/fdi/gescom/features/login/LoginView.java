package es.ucm.fdi.gescom.features.login;

import es.ucm.fdi.gescom.base.BaseView;

public interface LoginView extends BaseView {
    void loginSuccessful();
    void loginFailure();
    void fillingFailure();
    //reset password
}
