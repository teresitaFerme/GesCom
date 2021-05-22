package es.ucm.fdi.gescom.features.user_management;

import android.content.Context;

import java.util.ArrayList;

import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.datacache.User;

public class UserManagementPresenter extends BasePresenter {
    private UserManagementView mView;
    private UserManagementModel mModel;

    UserManagementPresenter(UserManagementView view){
        mView = view;
        mModel = new UserManagementModel((Context) view);
    }

    public ArrayList<User> getCommunityUsers() {
        ArrayList<User> users = mModel.retrieveCommunityUsers();
        return users;
    }

    public void validateDeleteUser(Integer petId) {
        mModel.deleteUser(petId);
    }
}
