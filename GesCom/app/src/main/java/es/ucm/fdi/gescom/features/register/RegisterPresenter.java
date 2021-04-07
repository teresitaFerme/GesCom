package es.ucm.fdi.gescom.features.register;

import android.content.Context;

import es.ucm.fdi.gescom.base.BasePresenter;

public class RegisterPresenter extends BasePresenter {
    private RegisterView mRegisterView;
    private RegisterModel mRegisterModel;

    public RegisterPresenter(RegisterView registerView){
        mRegisterView = registerView;
        mRegisterModel = new RegisterModel((Context) registerView);
    }

    public void validateRegister(String commName, String username) {
        if (username.length() != 0 && commName.length() != 0) {
            if (!mRegisterModel.getUsername(username)) {
                if (!mRegisterModel.getCommunityName(commName)) {
                    long admin_id = mRegisterModel.registerUser(username, commName);
                    mRegisterModel.registerCommunity(commName, admin_id);
                    mRegisterView.registerSuccessful();
                } else mRegisterView.registerCommunityFailure();
            } else mRegisterView.registerUserFailure();
        }else mRegisterView.fillingFailure();
    }
}
