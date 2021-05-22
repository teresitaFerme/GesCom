package es.ucm.fdi.gescom.features.avisos;

import es.ucm.fdi.gescom.base.BaseView;

public interface AvisosView extends BaseView {

    void launchAddAviso();
    void deleteAviso(int id);
    void editAviso(int id);
    void modifyAviso(int position, boolean delete, boolean edit);
    void populateRecyclerView();
}
