package es.ucm.fdi.gescom.features.userdisplay;

import es.ucm.fdi.gescom.base.BaseView;

public interface UserDisplayView extends BaseView {
    void bindViews();
    void setUserInfo(String username, String role, String comunidad);

    void logOut();

    void changePassword();
}
