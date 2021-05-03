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
            if(!mModel.validateData(dni, clave)) mView.wrongData("Dni o clave incorrectos");
        }else{
            mView.wrongData("Por favor rellene todos los campos");
        }
    }

    public void validate(String username, String pass, String passRepeat, String dni) {
        //TODO meter validacion del dni en la funcion validate de arriba y en el initialize users
        if (username.length() != 0 && pass.length() != 0 && passRepeat.length() != 0) {
            if(esDni(dni)){
                if(contieneSoloLetras(username)){
                    if(pass.equals(passRepeat)){
                        if(pass.length() >= 8){
                            if (!mModel.getUsername(username)) {
                                mModel.registerUser(username, pass, dni);
                                mView.initSuccessful();
                            } else mView.initFailure("Ese usuario ya está cogido. Introduzca otro usuario.");//TODO borrar aqui el campo usuario
                        }else  mView.initFailure("La contraseña debe tener 8 carácteres como mínimo.");
                    }else mView.initFailure("Las contraseñas no coinciden");//TODO hacer que se borren los campos de las contraseñas
                } else mView.initFailure("El usuario solo puede contener letras, números y barra baja.");
            }else mView.initFailure("Introduce un DNI válido.");
        }else mView.initFailure("Por favor rellene todos los campos");
    }

    public static boolean contieneSoloLetras(String cadena) {
        for (int x = 0; x < cadena.length(); x++) {
            char c = cadena.charAt(x);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_' || (c >= '0' && c <='9'))) {
                return false;
            }
        }
        return true;
    }

    private static boolean esDni(String cadena){
        if(cadena.length() != 9){
            return false;
        }else{
            for (int x = 0; x < cadena.length() - 1; x++) {
                char c = cadena.charAt(x);
                if (x < 8 && !(c >= '0' && c <='9')) {
                    return false;
                }
            }
            char c = cadena.charAt(8);
            if(!(c >= 'A' && c <= 'Z')){
                return false;
            }
            return true;
        }
    }

}
