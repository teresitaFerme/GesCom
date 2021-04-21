package es.ucm.fdi.gescom.features.register_user;

import es.ucm.fdi.gescom.base.BaseView;

public interface RegisterUserView extends BaseView {
    void correctData();
    void wrongData(String message);
}
