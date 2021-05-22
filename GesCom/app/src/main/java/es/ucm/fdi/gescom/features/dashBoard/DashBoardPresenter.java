package es.ucm.fdi.gescom.features.dashBoard;

import android.content.Context;

import java.util.ArrayList;

import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.datacache.Aviso;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.datacache.Incidencia;
import es.ucm.fdi.gescom.datacache.Votacion;

public class DashBoardPresenter extends BasePresenter {
    private DashBoardView mView;
    private DashBoardModel mModel;

    DashBoardPresenter(DashBoardView view){
        mView = view;
        mModel = new DashBoardModel((Context) view);
    }

    public ArrayList<Incidencia> getIncidencias() {
        ArrayList<Incidencia> list = mModel.getLastIncidences();
        return list;
    }

    public ArrayList<Aviso> getAvisos() {
        ArrayList<Aviso> list = mModel.getLastAvisos();
        return list;
    }

    public void checkAdmin() {
        if (GesComApp.getUser().getLocalizer().equals("Administrador")) {
            mView.drawIncidences();
            mView.hideUserShortcuts();
        } else{
            mView.hideIncidences();
            mView.bindUserShortcuts();
        }
    }

    public void launchAddIncidence() {
        mView.launchAddIncidence();
    }

    public ArrayList<Votacion> getVotes() {
        ArrayList<Votacion> list = mModel.getLastVotes();
        return list;
    }
}
