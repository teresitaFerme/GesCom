package es.ucm.fdi.gescom.features.userdisplay;

import es.ucm.fdi.gescom.base.BaseView;

public interface UserDisplayView extends BaseView {
    void setUserInfo(String username, String role);
}
