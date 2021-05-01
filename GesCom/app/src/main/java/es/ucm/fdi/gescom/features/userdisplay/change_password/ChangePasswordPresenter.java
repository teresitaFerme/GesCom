package es.ucm.fdi.gescom.features.userdisplay.change_password;

import android.content.Context;

import es.ucm.fdi.gescom.base.BasePresenter;

public class ChangePasswordPresenter extends BasePresenter {
    private ChangePasswordView mView;
    private ChangePasswordModel mModel;

    public ChangePasswordPresenter(ChangePasswordView view){
        mView = view;
        mModel = new ChangePasswordModel((Context) view);
    }

    public void validateData(String pass, String newPass, String newPassRepeat) {
        if(pass.length() != 0 && newPass.length() != 0 && newPassRepeat.length() != 0){
            if(newPass.equals(newPassRepeat)){
                if(mModel.changePassword(pass, newPass)){
                    mView.launchUserDisplay();
                } else mView.notActualPassword();
            }else mView.noMatchingPasswords();
        }else mView.fillingFailure();
    }
}
