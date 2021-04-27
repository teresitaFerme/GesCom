package es.ucm.fdi.gescom.features.loginregister;

import es.ucm.fdi.gescom.base.BaseView;

public interface LoginRegisterView extends BaseView {
    void registerCommunity();
    void registerUser();
    void loginSuccessful();
    void loginFailure();
    void fillingFailure();
}
