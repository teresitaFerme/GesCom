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

    public AvisosPresenter(AvisosView view){
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

    public void editarAviso() {
        mView.launchEditarAviso();
    }

    //TODO L- Pasar variable para el mModel, el id de mi fila
    public void eliminarAviso() {
        mModel.eliminarAviso();
    }

    public ArrayList<Aviso> getAvisos() {
        ArrayList<Aviso> list = mModel.getAvisos();
        return list;
    }
}
