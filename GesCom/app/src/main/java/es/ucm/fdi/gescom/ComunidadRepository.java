package es.ucm.fdi.gescom;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import es.ucm.fdi.gescom.roomdatabase.Comunidad;
import es.ucm.fdi.gescom.roomdatabase.ComunidadDao;
import es.ucm.fdi.gescom.roomdatabase.ComunidadRoomDatabase;

public class ComunidadRepository {
    private ComunidadDao mComunidadDao;
    private LiveData<List<Comunidad>> mTodasComunidades;


    ComunidadRepository(Application application) {
        ComunidadRoomDatabase db = ComunidadRoomDatabase.getDatabase(application);
        mComunidadDao = db.comunidadDao();
        mTodasComunidades = mComunidadDao.getTodasComunidades();
    }

    LiveData<List<Comunidad>> getTodasComunidades() {
        return mTodasComunidades;
    }

    public void insert (Comunidad comunidad) {
        new insertAsyncTask(mComunidadDao).execute(comunidad);
    }

    private static class insertAsyncTask extends AsyncTask<Comunidad, Void, Void> {

        private ComunidadDao mAsyncTaskDao;

        insertAsyncTask(ComunidadDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Comunidad... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
