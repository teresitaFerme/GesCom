package es.ucm.fdi.gescom.features.dashBoard;

import es.ucm.fdi.gescom.base.BaseView;

public interface DashBoardView extends BaseView {
    void drawIncidences();
    void hideIncidences();
    void drawVotes();
    void hideVotes();
    void drawAvisos();
    void drawReservas();
    void hideReservas();
    void hideUserShortcuts();
    void bindUserShortcuts();
    void launchAddIncidence();
}
