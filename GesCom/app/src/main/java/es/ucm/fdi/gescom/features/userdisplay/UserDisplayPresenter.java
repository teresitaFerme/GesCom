package es.ucm.fdi.gescom.features.userdisplay;

import es.ucm.fdi.gescom.base.BasePresenter;

public class UserDisplayPresenter extends BasePresenter {
    private UserDisplayView mView;
    private UserDisplayModel mModel;

    UserDisplayPresenter(UserDisplayView view){
        mView = view;
        mModel = new UserDisplayModel();
    }
}
