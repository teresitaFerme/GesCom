package es.ucm.fdi.gescom.roomdatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Comunidad.class}, version = 1, exportSchema = false)//just one entity, exportschema keeps track of schema versions
public abstract class ComunidadRoomDatabase extends RoomDatabase {
    //define the daos
    public abstract ComunidadDao comunidadDao();

    private static ComunidadRoomDatabase INSTANCE;

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ComunidadDao mDao;
        String[] comunidades = {"dolphin", "crocodile", "cobra"};

        PopulateDbAsync(ComunidadRoomDatabase db) {
            mDao = db.comunidadDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            return null;
        }
    }

    public static ComunidadRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ComunidadRoomDatabase.class){
                if(INSTANCE == null){
                    //create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ComunidadRoomDatabase.class, "tabla_comunidades")
                            .fallbackToDestructiveMigration().addCallback(sRoomDatabaseCallback).build();
                    // Wipes and rebuilds instead of migrating
                    // if no Migration object.
                    // Migration is not part of this practical.

                }
            }
        }
        return INSTANCE;
    }
}
