package es.ucm.fdi.gescom.features.user_management;

import es.ucm.fdi.gescom.base.BaseView;

public interface UserManagementView extends BaseView {
    void populateRecyclerView();
    void addUser();
    void editUser();
}
