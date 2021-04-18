package es.ucm.fdi.gescom.features.register_comunidad.initialize_users;

import es.ucm.fdi.gescom.base.BaseView;

public interface UsersInitializationView extends BaseView {
    void validationSuccess();
    void validationFailure(String message);
}
