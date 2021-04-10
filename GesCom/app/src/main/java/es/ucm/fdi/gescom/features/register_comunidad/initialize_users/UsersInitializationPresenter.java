package es.ucm.fdi.gescom.features.register_comunidad.initialize_users;

import android.content.Context;

import es.ucm.fdi.gescom.base.BasePresenter;

public class UsersInitializationPresenter extends BasePresenter {
    private UsersInitializationView mView;
    private UsersInitializationModel mModel;

    UsersInitializationPresenter(UsersInitializationView view){
        mView = view;
        mModel = new UsersInitializationModel((Context) view);
    }


}
