package es.ucm.fdi.gescom.features.loginregister;

import android.content.Context;

import es.ucm.fdi.gescom.base.BasePresenter;

public class LoginRegisterPresenter extends BasePresenter {
    private LoginRegisterView mView;
    private LoginRegisterModel mLoginModel;

    //TODO el model de esta clase debería comprobar si hay sesión ya iniciada

    LoginRegisterPresenter(LoginRegisterView loginRegisterView) {
        mLoginModel = new LoginRegisterModel((Context) loginRegisterView);
        mView = loginRegisterView;
    }

    public void openCommunityRegistration() {
        mView.registerCommunity();
    }

    public void openUserRegistration() {
        mView.registerUser();
    }

    public void validateLogin(String username, String password) {
        if(username.length() != 0 && password.length() != 0){
            if(mLoginModel.validateLogin(username, password)) mView.loginSuccessful();
            else mView.loginFailure();
        }else{
            mView.fillingFailure();
        }

    }
}
