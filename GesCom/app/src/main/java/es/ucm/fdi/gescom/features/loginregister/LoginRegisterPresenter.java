package es.ucm.fdi.gescom.features.loginregister;

import es.ucm.fdi.gescom.base.BasePresenter;

public class LoginRegisterPresenter extends BasePresenter {
    private LoginRegisterView mView;
    //TODO el model de esta clase debería comprobar si hay sesión ya iniciada

    LoginRegisterPresenter(LoginRegisterView loginRegisterView) {
        mView = loginRegisterView;
    }

    public void openLogin() {
        mView.login();
    }

    public void openCommunityRegistration() {
        mView.registerCommunity();
    }

    public void openUserRegistration() {
        mView.registerUser();
    }
}
