package es.ucm.fdi.gescom.features.votaciones;

import android.content.Context;

import java.util.ArrayList;

import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.datacache.Votacion;

public class VotacionesPresenter extends BasePresenter {
    private VotacionesView mView;
    private VotacionesModel mModel;

    public VotacionesPresenter(VotacionesView view){
        mView = view;
        mModel = new VotacionesModel((Context) view);

    }

    public ArrayList<Votacion> getVotacionesPendientes() {
        return null;
    }

    public ArrayList<Votacion> getVotacionesAnteriores() {
        return null;
    }
}
