package es.ucm.fdi.gescom.features.register_user.initialize_account;

import es.ucm.fdi.gescom.base.BaseView;

public interface InitAccountView extends BaseView {
    void initSuccessful();
    void initFailure(String message);
}
