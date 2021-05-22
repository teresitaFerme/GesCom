package es.ucm.fdi.gescom.features.user_management.new_user;

import es.ucm.fdi.gescom.base.BaseView;

public interface AddUserView extends BaseView {
    void wrongData(String message);
    void initSuccessful();
    void initFailure(String message);
}
