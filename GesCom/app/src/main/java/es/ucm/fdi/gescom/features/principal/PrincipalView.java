package es.ucm.fdi.gescom.features.principal;

import es.ucm.fdi.gescom.base.BaseView;

public interface PrincipalView extends BaseView {
    void drawIncidences();
    void hideIncidences();
    void drawVotes();
    void hideUserShortcuts();

    void bindUserShortcuts();

    void launchAddIncidence();
}
