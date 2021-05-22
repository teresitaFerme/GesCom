package es.ucm.fdi.gescom.features.user_management;

import es.ucm.fdi.gescom.base.BaseView;

public interface UserManagementView extends BaseView {
    void populateRecyclerView();
    void addUser();
    void editUser(int userid);
    void deleteUser(int userid);
    void onClick(int position, boolean edit, boolean delete);
}
