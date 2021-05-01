package es.ucm.fdi.gescom.features.userdisplay.change_password;

import es.ucm.fdi.gescom.base.BaseView;

public interface ChangePasswordView extends BaseView {
    void noMatchingPasswords();

    void fillingFailure();

    void notActualPassword();

    void launchUserDisplay();

    void samePassword();
}
