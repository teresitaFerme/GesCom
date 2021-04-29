package es.ucm.fdi.gescom.features.avisos;

import android.content.Context;

import java.util.ArrayList;

import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.datacache.Aviso;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.datacache.Votacion;

public class AvisosPresenter extends BasePresenter {
    private AvisosView  mView;
    private AvisosModel mModel;

    AvisosPresenter(AvisosView view){
        mView = view;
        mModel = new AvisosModel((Context) view);
    }

    public boolean checkAdmin() {
        if (GesComApp.getUser().getLocalizer().equals("Administrador")) {
            return true;
        } else return false;
    }

    public void addAviso() {
        mView.launchAddAviso();
    }

    public ArrayList<Aviso> getAvisos() {
        ArrayList<Aviso> list = mModel.getAvisos();
        return list;
    }
}
