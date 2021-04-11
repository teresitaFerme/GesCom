package es.ucm.fdi.gescom.datacache;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Comunidad {

    private Integer id;

    private String direccion;

    public Comunidad(Integer id,  String direccion) {
        this.id = id;
        this.direccion = direccion;
    }//the parameter can never be null

    public Comunidad getComunidad(){return this;}

    public Integer getId(){return this.id;}

    public String getDireccion(){return this.direccion;}
}
