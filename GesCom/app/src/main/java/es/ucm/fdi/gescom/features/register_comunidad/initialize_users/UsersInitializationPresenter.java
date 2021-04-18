package es.ucm.fdi.gescom.features.register_comunidad.initialize_users;

import android.content.Context;

import java.util.ArrayList;

import es.ucm.fdi.gescom.base.BasePresenter;

public class UsersInitializationPresenter extends BasePresenter {
    private UsersInitializationView mView;
    private UsersInitializationModel mModel;

    UsersInitializationPresenter(UsersInitializationView view){
        mView = view;
        mModel = new UsersInitializationModel((Context) view);
    }

    public void validateUsers(ArrayList<InitializableUser> mUsers) {
        String message = "";
        for(int i = 0; i< mUsers.size(); i++){
            if(mUsers.get(i).getUsername().equals("") || mUsers.get(i).getLocalizer().equals("")) {
                message = "Por favor rellena todos los campos";
                mView.validationFailure(message);
                return;
            }else if(mModel.getUsername(mUsers.get(i).getUsername())){
                message = "Ese usuario ya pertenece a otra comunidad";
                mView.validationFailure(message);
                mUsers.get(i).setUsername("", "");
                return;
            }//TODO METER AQUI MÁS VALIDACIONES DEL TIPO QUE LOS LOCALIZADORES SEan DIFERENTES Y QUE LOS DNIS TENGAN FORMATO DNI
        }
        mModel.registerUsers(mUsers);
        mView.validationSuccess();
    }
}
