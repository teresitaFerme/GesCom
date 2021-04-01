package es.ucm.fdi.gescom.features.login;

import es.ucm.fdi.gescom.base.BasePresenter;

public class LoginPresenter extends BasePresenter {
    private LoginView mLoginView;
    private LoginModel mLoginModel;

    LoginPresenter(LoginView loginView){
        mLoginView = loginView;
        mLoginModel = new LoginModel();
    }

    public void validateLogin(String username, String password) {
        if(!username.equals(null) && !password.equals(null)){
            if(mLoginModel.validateLogin(username, password)) mLoginView.loginSuccessful();
            else mLoginView.loginFailure();
        }else{
            mLoginView.fillingFailure();
        }

    }
}
