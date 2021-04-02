package es.ucm.fdi.gescom.features.login;

import es.ucm.fdi.gescom.base.BaseModel;

public class LoginModel extends BaseModel {

    public boolean validateLogin(String username, String password) {
        //TODO aqui lo que deberia hacerse es consultar la bbdd, comprobar que existe el usuario y que la contraseña es correcta
        //en caso afirmativo, guardar toda la info del usuario en una instancia singleton de la clase usuario

        //lo que está a continuación es para probar todas las lógicas de negocio
        if(username.equals("username") && password.equals("password")){
            return true;
        }
        else return false;
    }
}
