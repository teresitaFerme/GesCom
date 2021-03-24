package es.ucm.fdi.gescom.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ComunidadDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Comunidad comunidad);

    @Query("DELETE FROM tabla_comunidades")
    void deleteAll();

    @Query("SELECT * from tabla_comunidades ORDER BY id ASC")
    LiveData<List<Comunidad>> getTodasComunidades();//update livedata whenever the database is updated
}
