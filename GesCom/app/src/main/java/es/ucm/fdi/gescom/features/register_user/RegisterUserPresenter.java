package es.ucm.fdi.gescom.features.register_user;

import android.content.Context;

import es.ucm.fdi.gescom.base.BasePresenter;

public class RegisterUserPresenter extends BasePresenter {
    private RegisterUserView mView;
    private RegisterUserModel mModel;

    RegisterUserPresenter(RegisterUserView view){
        mView = view;
        mModel = new RegisterUserModel((Context) view);
    }

    public void validate(String dni, String clave) {
        if(dni.length() != 0 && clave.length() != 0){
            if(mModel.validateData(dni, clave)) mView.correctData();
            else mView.wrongData("Dni o clave incorrectos");
        }else{
            mView.wrongData("Por favor rellene todos los campos");
        }
    }
}
