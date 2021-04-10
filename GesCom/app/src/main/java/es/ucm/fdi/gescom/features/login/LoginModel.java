package es.ucm.fdi.gescom.features.login;

import es.ucm.fdi.gescom.base.BaseModel;
import es.ucm.fdi.gescom.datacache.GesComApp;

public class LoginModel extends BaseModel {

    public boolean validateLogin(String username, String password) {
        //TODO aqui lo que deberia hacerse es consultar la bbdd, comprobar que existe el usuario y que la contraseña es correcta
        //en caso afirmativo, guardar toda la info del usuario en una instancia singleton de la clase usuario

        //lo que está a continuación es para probar todas las lógicas de negocio
        if(username.equals("username") && password.equals("password")){
            GesComApp.getApp().setUser(username, password);;//aqui deberiamos meterle los datos reales
            return true;
        }
        else return false;
    }
}
