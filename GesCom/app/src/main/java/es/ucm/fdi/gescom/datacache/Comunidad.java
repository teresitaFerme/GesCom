package es.ucm.fdi.gescom.datacache;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tabla_comunidades")
public class Comunidad {
    @PrimaryKey
    @NonNull
    private Integer id;

    @ColumnInfo(name = "direccion")
    private String direccion;

    public Comunidad(@NonNull Integer id, @NonNull String direccion) {
        this.id = id;
        this.direccion = direccion;
    }//the parameter can never be null

    public Comunidad getComunidad(){return this;}

    @NonNull
    public Integer getId(){return this.id;}

    @NonNull
    public String getDireccion(){return this.direccion;}
}
