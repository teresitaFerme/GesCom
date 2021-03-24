package es.ucm.fdi.gescom;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.ucm.fdi.gescom.ComunidadRepository;
import es.ucm.fdi.gescom.roomdatabase.Comunidad;

public class ViewModel extends AndroidViewModel {
    private ComunidadRepository mRepository;
    private LiveData<List<Comunidad>> mTodasComunidades;

    public ViewModel (Application application) {
        super(application);
        mRepository = new ComunidadRepository(application);
        mTodasComunidades = mRepository.getTodasComunidades();
    }

   public LiveData<List<Comunidad>> getTodasComunidades() { return mTodasComunidades; }

    public void insert(Comunidad comunidad) { mRepository.insert(comunidad); }
}
