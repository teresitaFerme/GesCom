package es.ucm.fdi.gescom.features.register_user;

import es.ucm.fdi.gescom.base.BaseView;

public interface RegisterUserView extends BaseView {
    void wrongData(String message);
    void initSuccessful();
    void initFailure(String message);
}
