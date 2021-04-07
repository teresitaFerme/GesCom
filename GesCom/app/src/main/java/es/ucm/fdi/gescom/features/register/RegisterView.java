package es.ucm.fdi.gescom.features.register;

import es.ucm.fdi.gescom.base.BaseView;

public interface RegisterView extends BaseView {
    void registerSuccessful();
    void registerUserFailure();
    void registerCommunityFailure();
    void fillingFailure();
}
