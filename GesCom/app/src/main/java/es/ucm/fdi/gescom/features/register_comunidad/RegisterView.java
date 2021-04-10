package es.ucm.fdi.gescom.features.register_comunidad;

import es.ucm.fdi.gescom.base.BaseView;

public interface RegisterView extends BaseView {
    void registerSuccessful();
    void registerExistingUser();
    void registerCommunityFailure();
    void fillingFailure();
    void noMatchingPasswords();
    void registerUserServerFailure();
    void invalidDniFormat();
}
