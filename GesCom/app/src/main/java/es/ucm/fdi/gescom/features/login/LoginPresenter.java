package es.ucm.fdi.gescom.features.login;

import android.content.Context;

import es.ucm.fdi.gescom.base.BasePresenter;

public class LoginPresenter extends BasePresenter {
    private LoginView mLoginView;
    private LoginModel mLoginModel;

    LoginPresenter(LoginView loginView){
        mLoginView = loginView;
        mLoginModel = new LoginModel((Context) loginView);
    }

    public void validateLogin(String username, String password) {
        if(username.length() != 0 && password.length() != 0){
            if(mLoginModel.validateLogin(username, password)) mLoginView.loginSuccessful();
            else mLoginView.loginFailure();
        }else{
            mLoginView.fillingFailure();
        }

    }
}
