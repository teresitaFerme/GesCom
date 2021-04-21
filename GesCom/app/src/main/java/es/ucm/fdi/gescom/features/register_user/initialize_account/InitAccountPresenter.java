package es.ucm.fdi.gescom.features.register_user.initialize_account;

import android.content.Context;

import es.ucm.fdi.gescom.base.BasePresenter;

public class InitAccountPresenter extends BasePresenter {
    private InitAccountView mView;
    private InitAccountModel mModel;

    InitAccountPresenter(InitAccountView view){
        mView = view;
        mModel = new InitAccountModel((Context) view);
    }

    public void validate(String username, String pass, String passRepeat, String dni) {
        //TODO meter validaciones de username (sin signos) y de las contraseñas
        if (username.length() != 0 && pass.length() != 0 && passRepeat.length() != 0) {
            if(pass.equals(passRepeat)){//TODO: meterle aqui comprobaciones a la contraseña
                if (!mModel.getUsername(username)) {
                        mModel.registerUser(username, pass, dni);
                        mView.initSuccessful();
                } else mView.initFailure("Ese usuario ya está cogido. Introduzca otro usuario.");//TODO borrar aqui el campo usuario
            }else mView.initFailure("Las contraseñas no coinciden");//TODO hacer que se borren los campos de las contraseñas
        }else mView.initFailure("Por favor rellene todos los campos");
    }
}
