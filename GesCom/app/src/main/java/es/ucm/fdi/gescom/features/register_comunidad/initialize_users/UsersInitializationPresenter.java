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

    public void validateUsers(ArrayList<InitializableUser> mUsers,String comName,String nombreAdmin,String password, String dni) {
        long admin_id = mModel.registerUser(nombreAdmin, comName, password, dni, "Administrador");
        if(admin_id != -1){
            mModel.registerCommunity(comName, admin_id);
        }
        mView.validationSuccess();
    }
}
