package es.ucm.fdi.gescom.features.loginregister;

import android.view.View;

import es.ucm.fdi.gescom.base.BasePresenter;

public class LoginRegisterPresenter extends BasePresenter {
    private LoginRegisterView mView;
    private LoginRegisterModel mModel;
    //TODO el model de esta clase debería comprobar si hay sesión ya iniciada

    LoginRegisterPresenter(LoginRegisterView loginRegisterView){
        mView = loginRegisterView;
        mModel = new LoginRegisterModel();
    }

    public void openLogin() {
        mView.login();
    }

    public void openRegistration() {
        mView.register();
    }
}
